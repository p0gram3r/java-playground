package problem;

import java.math.BigInteger;

/**
 * For a positive integer n, define f(n) as the least positive multiple of n that, written in base 10, uses only digits ≤ 2.
 * 
 * Thus f(2)=2, f(3)=12, f(7)=21, f(42)=210, f(89)=1121222.
 * 
 * Also, sum(1,100, f(n)/n) = 11363107
 * 
 * Find sum(1,10000, f(n)/n)
 * 
 */
public class P303_MultiplesWithSmallDigits implements Problem {

    @Override
    public Object getSolution() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * For a positive integer n, define f(n) as the least positive multiple of n that, written in base 10, uses only digits ≤
     * 2.
     * 
     * @param n
     * @return
     */
    public BigInteger f(int n) {
        String regex = "^[012]*$";
        BigInteger multiple;

        for (int i = 1;; i += 1) {
            multiple = new BigInteger(Integer.toString(n));
            multiple = multiple.multiply(new BigInteger(Integer.toString(i)));

            if (multiple.toString().matches(regex)) {
                break;
            }
        }

        return multiple;
    }
}
