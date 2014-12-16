package problem;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class P079_PasscodeDerivationTest {
    @Test
    public void trivialTests() {
        runTest("1", "1", true);
        runTest("1", "2", false);
        runTest("12", "1", true);
        runTest("12", "2", true);
        runTest("12", "12", true);
        runTest("12", "23", false);
        runTest("123456789", "146", true);
        runTest("123456789", "164", false);
    }

    private void runTest(String passcode, String tan, boolean expectedResult) {
        assertEquals(expectedResult, P079_PasscodeDerivation.canGenerateTan(passcode, tan));
    }
}
