package problem;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * For a positive integer n, define f(n) as the least positive multiple of n that, written in base 10, uses only
 * digits ≤ 2.
 * <p>
 * Thus f(2)=2, f(3)=12, f(7)=21, f(42)=210, f(89)=1121222.
 * <p>
 * Also, sum(1,100, f(n)/n) = 11363107
 * <p>
 * Find sum(1,10000, f(n)/n)
 */
public class P303_MultiplesWithSmallDigits implements Problem {

    private static String F_REGEX = "^[012]*$";

    @Override
    public Object getSolution() {
        try {
            Map<Long, Long> mapping = getMappingUsingNumberGenerator(10000);

            Long result = 0L;
            for (Map.Entry<Long, Long> entry : mapping.entrySet()) {
                Long m = entry.getValue();
                result += m;
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This mapping only works for nCount <= 10000! this is because the calculation for 9999 would exceed Long
     * and we therefore would have to use BigInteger for all the calculation
     */
    Map<Long, Long> getMappingUsingNumberGenerator(int nCount) {
        Map<Long, Long> mapping = new TreeMap<>();
        ResultNumberGenerator resultNumberGenerator = new ResultNumberGenerator();

        // add trivial values
        mapping.put(1L, 1L);
        mapping.put(2L, 1L);

        // this value was derived from 9, 99 and 999
        // to calculate it, we would have to modify the code to use BigInteger as the product of these numbers
        // exceeds Long.MAX_VALUE
        mapping.put(9999L, 1111333355557778L);

        List<Long> keysToCheck = LongStream
                .rangeClosed(1, nCount)
                .mapToObj(l -> l)
                .collect(Collectors.toList());
        keysToCheck.removeAll(mapping.keySet());

        while (mapping.size() < nCount) {
            String value = resultNumberGenerator.nextValue();

            long product = Long.parseLong(value);
            for (Iterator<Long> it = keysToCheck.iterator(); it.hasNext(); ) {
                long n = it.next();
                long m = product / n;
                long product2 = n * m ;
                if (product2 == product) {
                    mapping.put(n, m);
                    it.remove();
                }
            }
        }

        return mapping;
    }

    class ResultNumberGenerator {
        private StringBuilder nextValue = new StringBuilder("1");
        private int lastCharIndex = 0;

        private String nextValue() {
            String next = nextValue.toString();
            updateCharAtIndex(lastCharIndex);
            return next;
        }

        private void updateCharAtIndex(int index) {
            if (index == -1) {
                lastCharIndex++;
                nextValue.insert(0, "1");
                return;
            }

            char c = nextValue.charAt(index);
            switch (c) {
                case '0':
                    nextValue.replace(index, index + 1, "1");
                    break;
                case '1':
                    nextValue.replace(index, index + 1, "2");
                    break;
                case '2':
                    nextValue.replace(index, index + 1, "0");
                    updateCharAtIndex(index - 1);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

}
