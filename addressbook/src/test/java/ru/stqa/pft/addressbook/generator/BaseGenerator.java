package ru.stqa.pft.addressbook.generator;

import java.util.Random;

public class BaseGenerator {

    private static Random random = new Random();

    static int generateRandom(int n) {
        return Math.abs(random.nextInt()) % n;
    }
}

