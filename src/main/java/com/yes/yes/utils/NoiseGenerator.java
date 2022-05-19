package com.yes.yes.utils;

public class NoiseGenerator {
    private NoiseGenerator(){}

    public static double calculate(int value)
    {
        return Math.abs(Math.sin(Math.tan(value) + Math.pow(value,2)) * Math.cos(Math.pow(value,2)) * Integer.MAX_VALUE);
    }
}
