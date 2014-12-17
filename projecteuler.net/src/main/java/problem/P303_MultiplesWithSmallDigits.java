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

    private static String F_REGEX = "^[012]*$";

    @Override
    public Object getSolution() {
        return sumFunctionQuotients(10);
    }

    /**
     * Returns the factor x so that f(n) = n * x returns a number only using digits ≤ 2
     * 
     * @param n
     * @return
     */
    protected BigInteger getFactorForF(int n) {
        BigInteger nAsBigInt = new BigInteger(Integer.toString(n));
        BigInteger factor = null;

        for (int i = 1;;) {
            nAsBigInt = new BigInteger(Integer.toString(n));
            factor = new BigInteger(Integer.toString(i));

            BigInteger multiple = nAsBigInt.multiply(factor);
            if (multiple.toString().matches(F_REGEX)) {
                break;
            }

            if (n % 10 == 9) {
                if (i % 10 == 0) {
                    i += 8;
                }
                else {
                    i += 1;
                }
            }
            else {
                i += 1;
            }
        }

        return factor;
    }

    // ///
    // old and slow implementation
    // ///

    /**
     * For a positive integer n, define f(n) as the least positive multiple of n that, written in base 10, uses only digits ≤
     * 2.
     * 
     * @param n
     * @return
     */
    protected BigInteger f(int n) {
        BigInteger multiple;

        for (int i = 1;; i += 1) {
            multiple = new BigInteger(Integer.toString(n));
            multiple = multiple.multiply(new BigInteger(Integer.toString(i)));

            if (multiple.toString().matches(F_REGEX)) {
                break;
            }
        }

        return multiple;
    }

    protected BigInteger sumFunctionQuotients(int maxN) {
        BigInteger result = BigInteger.ZERO;

        for (int n = 1; n <= maxN; n += 1) {
            // BigInteger functionResult = f(n);
            // BigInteger nAsBigInt = new BigInteger(Integer.toString(n));
            // BigInteger quot = functionResult.divide(nAsBigInt);
            //
            // result = result.add(quot);
            // System.out.println("  f: " + functionResult + "  n: " + nAsBigInt + "   = " + quot + " --> " + result);

            result = result.add(getFactorForF(n));
        }

        return result;
    }

}
