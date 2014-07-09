package problem49;

import java.util.ArrayList;
import java.util.List;

public class PrimePermutations
{

	// The arithmetic sequence, 1487, 4817, 8147, in which each of the terms
	// increases by 3330, is unusual in two ways: (i) each of the three terms
	// are prime, and, (ii) each of the 4-digit numbers are permutations of one
	// another.
	//
	// There are no arithmetic sequences made up of three 1-, 2-, or 3-digit
	// primes, exhibiting this property, but there is one other 4-digit
	// increasing sequence.
	//
	// What 12-digit number do you form by concatenating the three terms in this
	// sequence?

	public static void main(String[] args)
	{
		for (int number = 1001; number < 9999; number += 2)
		{
			if (!NumberUtils.isPrim(number))
			{
				continue;
			}

			List<Integer> permutions = NumberUtils.getPermutations(number);
			permutions = removeNonPrimes(permutions);

			if (permutions.size() < 3)
			{
				continue;
			}

			System.out.println(permutions);
		}

	}



	private static List<Integer> removeNonPrimes(List<Integer> numberList)
	{
		List<Integer> result = new ArrayList<Integer>(numberList.size());
		for (Integer i : numberList)
		{
			if (NumberUtils.isPrim(i))
			{
				result.add(i);
			}
		}
		return result;
	}

}
