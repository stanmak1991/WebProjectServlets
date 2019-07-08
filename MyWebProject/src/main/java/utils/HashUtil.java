package utils;

import org.apache.log4j.Logger;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class HashUtil {
    private static Logger logger = Logger.getLogger(HashUtil.class);

    public static String getSHA512SecurePassword(String passwordToHash, String salt) {
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = messageDigest.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder stringBuilder = new StringBuilder();
            for (int counter = 0; counter < bytes.length; counter++) {
                stringBuilder.append(Integer.toString((bytes[counter] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            logger.error("Can't find algorithm", e);
        }
        return generatedPassword;
    }

    public static String getRandomSalt() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }
}
