package utils;

import java.util.Random;

public class RandomHelper {

    public static Long getRandomCode() {
        Random random = new Random();
        Long codeConfirm = Long.valueOf(random.nextInt(9000) + 1000);
        return codeConfirm;
    }
}
