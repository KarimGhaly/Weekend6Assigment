package com.example.admin.weekend6assigment.JungleProblem;

import android.util.Log;

import com.example.admin.weekend6assigment.JungleProblem.Animal;

import java.util.Random;

/**
 * Created by Zack on 10/7/2017.
 */

public class Monkey extends Animal {
    private static final String TAG = "MonkeyTag";

    static int numMonkeys = 0;

    public Monkey() {
        numMonkeys+=1;
    }

    public int getNumMonkeys() {
        return numMonkeys;
    }

    @Override
    public void sleep() {
        super.sleep();
        Log.d(TAG, "sleep: " + energy);
    }

    @Override
    public void eatFood(String food) {
        super.eatFood(food);
        energy += 2;
        Log.d(TAG, "eatFood: " + food + " " + energy);
    }

    @Override
    public void makeASound() {
        super.makeASound();
        energy += -4;
        Log.d(TAG, "makeASound: "+ energy);
    }

    public void play(){
        if (energy >= 8 ){
            energy += -8;
            Log.d(TAG, "Oooo Oooo Oooo " + energy);

        } else
            Log.d(TAG, "Monkey is too tired " + energy);
    }


    @Override
    public void doAThing() {

        String food = findFood();

        Random rand = new Random();
        int i = rand.nextInt(3);
        Log.d(TAG, "doAThing: " + i);
        switch (i){

            case 0:
                makeASound();
                break;
            case 1:
                sleep();
                break;
            case 2:
                eatFood(food);
                break;
            case 3:
                play();
                break;
        }

    }


}
