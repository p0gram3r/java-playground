package ae.test.sudokusolver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import org.junit.Test;

public class FieldTest {

	@Test
	public void testGetPossibleValuesAfterConstruction() {
		Field field = new Field();

		assertThat(field.getValue(), is(nullValue()));
		assertThat(field.getPossibleValues(),
				contains(1, 2, 3, 4, 5, 6, 7, 8, 9));
	}

	@Test
	public void testGetPossibleValuesAfterSettingValue() {
		Field field = new Field();
		field.setValue(5);

		assertThat(field.getValue(), is(5));
		assertThat(field.getPossibleValues(), is(empty()));
	}

	@Test
	public void testRemovePossibility() {
		Field field = new Field();

		assertThat(field.getPossibleValues(),
				contains(1, 2, 3, 4, 5, 6, 7, 8, 9));

		field.removePossibility(5);
		assertThat(field.getPossibleValues(), contains(1, 2, 3, 4, 6, 7, 8, 9));

		field.removePossibility(5);
		assertThat(field.getPossibleValues(), contains(1, 2, 3, 4, 6, 7, 8, 9));
	}
}
