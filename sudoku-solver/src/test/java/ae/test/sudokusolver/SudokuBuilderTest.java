package ae.test.sudokusolver;

import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class SudokuBuilderTest {

    static final int DIMENSION = 4;

    private SudokuBuilder builder;

    @Before
    public void before() {
        this.builder = new SudokuBuilder(DIMENSION);
    }

    @Test
    public void initFieldReturnsBuilder() {
        SudokuBuilder returnValue = builder.initField(0, 0, 1);
        assertSame(builder, returnValue);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initFieldRejectsInvalidXCoordinate() {
        builder.initField(DIMENSION, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initFieldRejectsInvalidYCoordinate() {
        builder.initField(0, DIMENSION, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initFieldRejectsNegativeXCoordinate() {
        builder.initField(-5, 0, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void initFieldRejectsNegativeYCoordinate() {
        builder.initField(0, -5, 1);
    }
}
