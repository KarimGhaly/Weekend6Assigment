package com.example.admin.weekend6assigment.JungleProblem;

import android.support.annotation.NonNull;

import java.util.Random;

/**
 * Created by Zack on 10/7/2017.
 */

public class Animal {

    protected int energy = 20;

    public void makeASound() {
        energy += -3;
    }

    public void eatFood(String food) {
        energy += 5;
    }

    public void sleep() {
        energy += 10;
    }

    public void doAThing() {

        String food = findFood();

        Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0:
                makeASound();
                break;
            case 1:
                sleep();
                break;
            case 2:
                eatFood(food);
                break;
        }
    }

    @NonNull
    protected String findFood() {
        Random rand1 = new Random();
        int foodRand = rand1.nextInt(3);
        String food = "";

        switch (foodRand) {

            case 0:
                food = "meat";
                break;
            case 1:
                food = "fish";
                break;
            case 2:
                food = "bugs";
                break;
            case 3:
                food = "grain";
                break;
        }
        return food;
    }

}
