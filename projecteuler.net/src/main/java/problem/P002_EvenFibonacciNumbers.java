package problem;

public class P002_EvenFibonacciNumbers implements Problem {

    public Object getSolution() {
        int limit = 4000000;
        long sum = 0;

        // F0 and F1
        long a = 0;
        long b = 1;

        while (b < limit) {
            long temp = b;
            b = a + b;
            a = temp;

            if (b % 2 == 0) {
                sum += b;
            }
        }

        return sum;
    }
}
