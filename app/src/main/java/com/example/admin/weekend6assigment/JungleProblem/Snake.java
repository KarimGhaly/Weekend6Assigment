package com.example.admin.weekend6assigment.JungleProblem;

import android.util.Log;

/**
 * Created by Zack on 10/7/2017.
 */

public class Snake extends Animal {
    private static final String TAG = "SnakeTag";
    static int numSnakes = 0;

    public Snake() {
        numSnakes+=1;
    }

    public int getNumSnakes() {
        return numSnakes;
    }

    @Override
    public void sleep() {
        super.sleep();
        Log.d(TAG, "sleep: " + energy);
    }

    @Override
    public void eatFood(String food) {
        super.eatFood(food);
        Log.d(TAG, "eatFood: " + energy);
    }

    @Override
    public void makeASound() {
        super.makeASound();
        Log.d(TAG, "makeASound: ");
    }
}
