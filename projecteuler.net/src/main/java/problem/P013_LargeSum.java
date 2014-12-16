package problem;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class P013_LargeSum implements Problem {
    public Object getSolution() {
        BigInteger sum = BigInteger.ZERO;

        InputStream inStream = P013_LargeSum.class.getClassLoader().getResourceAsStream("problem13.txt");
        Scanner scanner = new Scanner(inStream);

        while (scanner.hasNext()) {
            String number = scanner.nextLine();
            sum = sum.add(new BigInteger(number));
        }

        String s = sum.toString();
        return s.substring(0, 10);
    }

}
