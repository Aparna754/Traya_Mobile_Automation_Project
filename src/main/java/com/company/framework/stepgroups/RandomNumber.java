package com.company.framework.stepgroups;

import java.util.Random;

public class RandomNumber {

    public static String generateMobileNumber() {
        Random rand = new Random();
        int[] digitCount = new int[10];
        StringBuilder number = new StringBuilder();

        int firstDigit = 2 + rand.nextInt(4); // 2,3,4,5
        number.append(firstDigit);
        digitCount[firstDigit]++;

        for (int pos = 1; pos < 10; pos++) {
            int digit;
            do {
                digit = rand.nextInt(10); // 0-9
            } while (digitCount[digit] >= 3);

            number.append(digit);
            digitCount[digit]++;
        }

        return number.toString();
    }

    public static String generateRandomAge() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateRandomAge'");
    }
}
