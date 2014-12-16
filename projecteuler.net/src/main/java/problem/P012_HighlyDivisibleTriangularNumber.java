package problem;

import java.util.Collection;

import utils.LongUtils;

public class P012_HighlyDivisibleTriangularNumber implements Problem {
    public Object getSolution() {
        int i = 1;
        long triangleNumber = 0;
        int divisorCount = 0;

        do {
            triangleNumber = calculateTriangleNumber(i);
            Collection<Long> allDivisors = LongUtils.getAllDivisors(triangleNumber);
            divisorCount = allDivisors.size();
            i = i + 1;
        } while (divisorCount < 500);

        return triangleNumber;
    }

    private static long calculateTriangleNumber(long n) {
        return n * (n + 1) / 2;
    }

}
