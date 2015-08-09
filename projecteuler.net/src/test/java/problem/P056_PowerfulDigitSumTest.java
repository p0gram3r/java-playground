package problem;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class P056_PowerfulDigitSumTest {

    private P056_PowerfulDigitSum problem;

    @Before
    public void before() {
        problem = new P056_PowerfulDigitSum();
    }

    @Test
    public void test() {

        BigInteger number = new BigInteger("123");
        long digitSum = problem.sumOfDigits(number);
        assertEquals(6L, digitSum);
    }
}
