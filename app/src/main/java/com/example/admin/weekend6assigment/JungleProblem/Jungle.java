package com.example.admin.weekend6assigment.JungleProblem;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zack on 10/7/2017.
 */

public class Jungle {

    private static final String TAG = "JungleTag";
    List<Monkey> monkeys;
    List<Snake> snakes;
    List<Tiger> tigers;

    public Jungle() {
        monkeys = new ArrayList<>();
        snakes = new ArrayList<>();
        tigers = new ArrayList<>();
        monkeys.add(new Monkey());
        monkeys.add(new Monkey());
        snakes.add(new Snake());
        snakes.add(new Snake());
        snakes.add(new Snake());
        snakes.add(new Snake());
        snakes.add(new Snake());
        tigers.add(new Tiger());
        doStuff();
        soundOff();
    }

    public void soundOff() {
        for (Monkey monkey:
             monkeys) {
            monkey.makeASound();
        }
        for (Snake snake:
             snakes) {
            snake.makeASound();
        }
        for (Tiger tiger :
                tigers) {
            tiger.makeASound();
        }
    }

    public void doStuff() {
        monkeys.get(0).makeASound();
        monkeys.get(0).play();
        monkeys.get(0).play();
        monkeys.get(0).play();
        monkeys.get(0).play();
        monkeys.get(0).makeASound();
        monkeys.get(0).makeASound();
        monkeys.get(0).eatFood("grain");
        monkeys.get(0).eatFood("grain");
        monkeys.get(0).eatFood("grain");
        monkeys.get(1).doAThing();
        monkeys.get(1).doAThing();
        monkeys.get(1).doAThing();
        monkeys.get(1).doAThing();
        monkeys.get(1).doAThing();
        monkeys.get(1).doAThing();
        monkeys.get(1).doAThing();
        monkeys.get(1).makeASound();
        snakes.get(0).makeASound();
        snakes.get(1).doAThing();
        snakes.get(1).doAThing();
        snakes.get(1).doAThing();
        snakes.get(1).doAThing();
        snakes.get(1).doAThing();
        snakes.get(1).doAThing();
        snakes.get(1).doAThing();
        tigers.get(0).sleep();
        tigers.get(0).makeASound();
        tigers.get(0).eatFood("grain");
        Log.d(TAG, "soundOff: monkeys = " + monkeys.get(0).getNumMonkeys());
        Log.d(TAG, "soundOff: snakes = " + snakes.get(0).getNumSnakes());
        Log.d(TAG, "soundOff: tigers = " + tigers.get(0).getNumTigers());

    }

}
