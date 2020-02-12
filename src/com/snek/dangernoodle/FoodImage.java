package com.snek.dangernoodle;

import java.util.Random;

public enum FoodImage {
    APPLE("src/resources/apple.png"),
    DOT("src/resources/dot.png");

    private final String text;

    FoodImage(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
    public static FoodImage getRandomImage() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }


}
