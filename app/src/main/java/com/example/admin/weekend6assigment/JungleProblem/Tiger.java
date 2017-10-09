package com.example.admin.weekend6assigment.JungleProblem;

import android.util.Log;

import com.example.admin.weekend6assigment.JungleProblem.Animal;

/**
 * Created by Zack on 10/7/2017.
 */





public class Tiger extends Animal {

    private static final String TAG = "TigerTag";

    static int numTigers = 0;

    public Tiger() {
        numTigers+=1;
    }

    public int getNumTigers() {
        return numTigers;
    }

    @Override
    public void sleep(){
        super.sleep();
        energy += 5; //Tigers get +5 energy for sleeping.
        Log.d(TAG, "sleep: " + energy);
    }

    @Override
    public void makeASound() {
        super.makeASound();
        Log.d(TAG, "makeASound: " + energy);
    }

    @Override
    public void eatFood(String food) {
        if (food != "grain") {
            super.eatFood(food);
            Log.d(TAG, "eatFood: " + energy);

        } else
            Log.d(TAG, "I can't eat grain. I have a sensitive digestive system.");
    }
}
