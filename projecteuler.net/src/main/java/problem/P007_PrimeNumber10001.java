package problem;

import utils.IntegerUtils;

public class P007_PrimeNumber10001 implements Problem {

    public Object getSolution() {
        int currentNumber = 0;
        int index = 0;
        do {
            currentNumber++;

            if (IntegerUtils.isPrim(currentNumber)) {
                index++;
                // System.out.println("index " + index + ": " + currentNumber);
            }

        } while (index < 10001);

        return currentNumber;
    }
}
