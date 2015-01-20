package ae.picarchive;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

public class PicArchiveMain {

    public static void main(String[] args) {

        // @formatter:off
        List<String> strings = Arrays.asList(
                "https://www.google.de/images/srpr/logo11w.png",
                "https://www.google.com/images/errors/robot.png",
                "https://www.google.com/images/errors/robot.png6514654654646466464649471687631064/y47r6t84s61d6v8/7d6r31tg3s8df7v/87asd3r13s5d4f97as6d1r3///////210drg3f46x3f1g",
                "https://www.google.com/images/errors/robot.png6514654654646466464649471687631064/y47r6t84s61d6v8/7d6r31tg3s8df7v/87asd3r13s5d4f97as6d1r3///////210drg3f46x3f1",
                "robot.png",
                "r"
                );
        // @formatter:on

        for (String s : strings) {
            System.out.println(s.hashCode());
            System.out.println(generateMD5(s));
        }
    }

    private static String generateMD5(String message) {
        return hashString(message, "MD5");
    }

    private static String hashString(String message, String algorithm) {

        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hashedBytes = digest.digest(message.getBytes("UTF-8"));

            return convertByteArrayToHexString(hashedBytes);
        }
        catch (Exception ex) {
            throw new RuntimeException("Could not generate hash from String " + message, ex);
        }
    }

    private static String convertByteArrayToHexString(byte[] arrayBytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < arrayBytes.length; i++) {
            stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return stringBuffer.toString();
    }
}
