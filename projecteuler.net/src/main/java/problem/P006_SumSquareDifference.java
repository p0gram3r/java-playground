package problem;

public class P006_SumSquareDifference implements Problem {

    public Object getSolution() {
        int N = 100;

        long sum1 = 0;
        long sum2 = 0;
        for (int i = 1; i <= N; i++) {
            sum1 += i * i;
            sum2 += i;
        }

        sum2 = sum2 * sum2;

        return sum2 - sum1;
    }
}
