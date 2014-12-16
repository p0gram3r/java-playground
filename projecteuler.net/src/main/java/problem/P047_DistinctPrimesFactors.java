package problem;

import java.util.Set;
import java.util.TreeSet;

import utils.LongUtils;

public class P047_DistinctPrimesFactors implements Problem {

    public Object getSolution() {
        int LIMIT = 4;

        long result = 2;
        int counter = 0;
        for (long currentNumber = 2; counter < LIMIT; currentNumber += 1) {

            if (uniquePrimeFactors(currentNumber).size() == LIMIT) {
                result = currentNumber - counter;
                counter += 1;
            }
            else {
                counter = 0;
            }
        }

        return result;
    }

    private static Set<Long> uniquePrimeFactors(long number) {
        return new TreeSet<Long>(LongUtils.getPrimFactors(number));
    }
}
