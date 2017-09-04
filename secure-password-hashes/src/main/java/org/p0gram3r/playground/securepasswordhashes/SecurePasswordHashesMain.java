package org.p0gram3r.playground.securepasswordhashes;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class SecurePasswordHashesMain {

    public static void main(String[] args) {

        String password = "secretPassword123!";
        String salt = "someCrazySalt";
        int iterations = 1000;
        int keyLength = 192;

        String hash = hashPassword(password, salt, iterations, keyLength);

        System.out.println(hash);
    }

    public static String hashPassword(String password, String salt, int iterations, int keyLength) {
        try {
            char[] passwordChars = password.toCharArray();
            byte[] saltBytes = salt.getBytes();

            KeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey secretKey = key.generateSecret(spec);

            byte[] hashedPassword = secretKey.getEncoded();
            return String.format("%x", new BigInteger(hashedPassword));
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
