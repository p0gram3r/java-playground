package problem49;

import IntegerUtils;

import java.util.Collection;

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
		for (int number1 = 1001; number1 < 3333; number1 += 2)
		{
			if (!IntegerUtils.isPrim(number1))
			{
				continue;
			}

			Collection<Integer> permutions = IntegerUtils.getPermutations(number1);

			for (int number2 : permutions)
			{
				if (number2 == number1)
				{
					continue;
				}

				if (!IntegerUtils.isPrim(number2) || !permutions.contains(number2))
				{
					continue;
				}

				int delta = number2 - number1;
				int number3 = number2 + delta;

				if (!IntegerUtils.isPrim(number3) || !permutions.contains(number3))
				{
					continue;
				}

				System.out.println("" + number1 + number2 + number3);
			}

		}

	}

}
