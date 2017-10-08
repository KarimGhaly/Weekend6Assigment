package com.example.admin.weekend6assigment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.admin.weekend6assigment.db.DaoMaster;
import com.example.admin.weekend6assigment.db.DaoSession;
import com.example.admin.weekend6assigment.db.User;
import com.example.admin.weekend6assigment.db.UserDao;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivityTAG";
    private Bitmap imageBitmap;
    private boolean flagImgCaptured =false;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    TwitterLoginButton loginButton;
    private DaoSession daoSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        IntiliazeTwitter();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupFacebook();
        setupTwitter();
        String EncodedMessage = EncodeString("Errors in strategy cannot be correct through tactical maneuvers");
        String CountedLetters = CountChars("Hello there! Apple!");
        Log.d(TAG, "EncodedMessage: "+EncodedMessage);
        Log.d(TAG, "CountedLetters: "+CountedLetters);
        daoSession = ((AppController) getApplication()).getDaoSession();
        AddUsertoDB();
        ShowUsersFromDB();
    }

    public void setupFacebook(){
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(MainActivity.this, "Shared Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(MainActivity.this, "Failed to Share", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onError: "+error.toString());
            }
        });
    }

    public void setupTwitter(){
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "failure: "+exception.toString());
            }
        });
    }

    public void IntiliazeTwitter(){
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY),getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
    }

    public String EncodeString(String orgStr)
    {
        orgStr = orgStr.toLowerCase();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String EncodedString ="";
        for (int i = 0; i < orgStr.length() ; i++) {
            if(alphabet.indexOf(orgStr.charAt(i))>-1) {
                EncodedString += alphabet.charAt(25 - alphabet.indexOf(orgStr.charAt(i)));
            }
            else
            {
                EncodedString += orgStr.charAt(i);
            }
        }
            return EncodedString;
    }

    public String CountChars(String orgStr)
    {
        HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        for (int i = 0; i <alphabet.length ; i++) {
            charCountMap.put(alphabet[i], 0);
        }
        for (int i = 0; i < orgStr.length(); i++) {
            char c = orgStr.toLowerCase().charAt(i);
            if(charCountMap.containsKey(c))
            {
                charCountMap.put(c, charCountMap.get(c)+1);
            }
        }
        return charCountMap.toString();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            flagImgCaptured = true;
            ShareonFB();
        }
        else
        {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            loginButton.onActivityResult(requestCode, resultCode, data);
           // Toast.makeText(this, "Image Failed", Toast.LENGTH_SHORT).show();
        }

    }

    public void getPhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    public void TweetonTwitter(View view) {
        Tweet();
    }
    public void ShareonFB(){
        SharePhoto sharePhoto1 = new SharePhoto.Builder().setBitmap(imageBitmap).build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(sharePhoto1)
                .build();
        shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
    }
    public void Tweet(){
        final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                .getActiveSession();
        final Intent intent = new ComposerActivity.Builder(MainActivity.this)
                .session(session)
                .text("")
                .hashtags("")
                .createIntent();
        startActivity(intent);
    }

    public void AddUsertoDB(){
        User user = new User();
        user.setFull_name("Karim K Ghaly");
        user.setUser_id(10);
        user.setEmail("krmkarm@yahoo.com");
        daoSession.insert(user);
    }
    public void ShowUsersFromDB(){
      UserDao userDao =  daoSession.getUserDao();
        List<User> users = userDao.loadAll();
        for(User u: users)
        {
            Log.d(TAG, "ShowUsersFromDB: "+u.getFull_name());
        }
    }

}
