package org.p0gram3r.playground.securepasswordhashes;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SecurePasswordHashesMain {

    public static void main(String[] args) throws Exception {

        String password = "secretPassword123!";
        String salt = "salt";
        int iterations = 1;
        int keyLength = 19;

        String hash = hashPassword(password, salt, iterations, keyLength);
        System.out.println(hash);
    }

    public static String hashPassword(String password, String salt, int iterations, int keyLength)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        char[] passwordChars = password.toCharArray();
        byte[] saltBytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, iterations, keyLength);
        SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = key.generateSecret(spec).getEncoded();
        return String.format("%x", new BigInteger(hashedPassword));
    }
}
