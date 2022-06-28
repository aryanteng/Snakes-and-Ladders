package com.example.snakesandladders;

import java.util.Random;

public class Dice {
    public int RollDice(){
        Random random = new Random();
        return 1 + random.nextInt(6);
    }
}
