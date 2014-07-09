package problem49;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class NumberUtilsTest
{
	@Test
	public void getPermutationsForOneDigits()
	{
		List<Integer> permutions = NumberUtils.getPermutations(1);

		assertEquals(1, permutions.size());
		assertTrue(permutions.contains(1));
	}



	@Test
	public void getPermutationsForTwoDigits()
	{
		List<Integer> permutions = NumberUtils.getPermutations(12);

		assertEquals(2, permutions.size());
		assertTrue(permutions.contains(12));
		assertTrue(permutions.contains(21));
	}



	@Test
	public void getPermutationsForThreeDigits()
	{
		List<Integer> permutions = NumberUtils.getPermutations(123);

		assertEquals(6, permutions.size());
		assertTrue(permutions.contains(123));
		assertTrue(permutions.contains(132));
		assertTrue(permutions.contains(213));
		assertTrue(permutions.contains(231));
		assertTrue(permutions.contains(312));
		assertTrue(permutions.contains(321));
	}
}
