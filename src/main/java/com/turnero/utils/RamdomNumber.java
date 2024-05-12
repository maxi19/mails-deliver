package com.turnero.utils;

import java.util.Random;

public class RamdomNumber {

    public static String getRamdomNumber(){
        Random rand = new Random();
        int n = rand.nextInt((int) Math.pow(10, 10));
        String number = String.format("%010d", n);
        return number;
    }

}
