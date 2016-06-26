/**
 *  Class random number generation
 */

package ru.stqa.pft.addressbook.generator;

import java.util.Random;

public class BaseGenerator {

    private static Random random = new Random();

    public static int generateRandom(int n) {
        return Math.abs(random.nextInt()) % n;
    }
}

