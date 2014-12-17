package problem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class P303_MultiplesWithSmallDigitsTest {

    private P303_MultiplesWithSmallDigits p;

    @Before
    public void before() {
        p = new P303_MultiplesWithSmallDigits();
    }

    @Test
    public void testAsdf() {
        System.out.println(p.getFactorForF(99));
        fail();
    }

    @Ignore
    @Test
    public void testSumFunctionQuotients() {
        assertEquals(new BigInteger("11363107"), p.sumFunctionQuotients(100));
    }

    @Ignore
    @Test
    public void testFFunction() {
        assertFResult(1, 1);
        assertFResult(2, 2);
        assertFResult(3, 12);
        assertFResult(7, 21);
        assertFResult(42, 210);
        assertFResult(89, 1121222);
    }

    private void assertFResult(int n, int expectedResult) {
        assertEquals("f(" + n + ")", new BigInteger(Integer.toString(expectedResult)), p.f(n));
    }
}
