package problem;

import java.math.BigInteger;

public class P056_PowerfulDigitSum implements Problem {

    @Override
    public Object getSolution() {
        long currentMaxSum = 0L;

        for (int a = 1; a < 100; a++) {
            for (int b = 1; b < 100; b++) {
                BigInteger power = buildBigInt(a, b);
                long sum = sumOfDigits(power);

                if (sum > currentMaxSum) {
                    System.out.println(String.format("a=%d b=%d ==> %d", a, b, sum));
                    currentMaxSum = sum;
                }
            }
        }
        return currentMaxSum;
    }

    private BigInteger buildBigInt(int a, int b) {
        return new BigInteger(Integer.toString(a)).pow(b);
    }

    long sumOfDigits(BigInteger number) {
        long sum = 0L;

        String numberString = number.toString();

        int len = numberString.length();
        for (int i = 0; i < len; i++) {
            int digit = Integer.parseInt(numberString.substring(i, i + 1));
            sum += digit;
        }

        return sum;
    }
}
