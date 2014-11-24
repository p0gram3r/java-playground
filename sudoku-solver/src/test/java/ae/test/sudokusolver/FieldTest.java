package ae.test.sudokusolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class FieldTest {

    @Test
    public void testGetPossibleValuesAfterConstruction() {
        Field field = new Field();

        assertThat(field.getValue(), is(nullValue()));
        assertThat(field.getPossibleValues(), containsInAnyOrder(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }
}
