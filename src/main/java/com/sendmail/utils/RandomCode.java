package com.sendmail.utils;

import java.security.SecureRandom;

public class RandomCode {

    private static final String CHAR = "QWERTYUIOPLKJHGFDSAZXCVBNM";
    private static final String NUMBER = "0123456789";

    private static final String DATA_RANDOM = CHAR + NUMBER;
    private static SecureRandom random = new SecureRandom();


    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {

            // 0-62 (exclusive), random returns 0-61
            int rndCharAt = random.nextInt(DATA_RANDOM.length());
            char rndChar = DATA_RANDOM.charAt(rndCharAt);

            sb.append(rndChar);
        }
        return sb.toString();
    }
}
