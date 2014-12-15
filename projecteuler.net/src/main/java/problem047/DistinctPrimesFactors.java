package problem;

import java.util.Set;
import java.util.TreeSet;

import utils.LongUtils;

public class DistinctPrimesFactors {

    public static void main(String[] args) {
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

        System.out.println(result);
    }

    private static Set<Long> uniquePrimeFactors(long number) {
        return new TreeSet<Long>(LongUtils.getPrimFactors(number));
    }
}
