package com.company.framework.stepgroups;

public class RandomAge {

    public static String generateRandomAge() {
        int minAge = 18; 
        int maxAge = 60;
        int randomAge = (int) (Math.random() * (maxAge - minAge + 1)) + minAge;

        return String.valueOf(randomAge);
    }

}