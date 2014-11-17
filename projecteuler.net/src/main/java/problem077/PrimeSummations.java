package problem077;

import java.util.LinkedList;
import java.util.List;

import utils.IntegerUtils;

/**
 * Solution is based on the "coin change" problem, see
 * http://www.algorithmist.com/index.php/Coin_Change
 * 
 */
public class PrimeSummations
{
	public static void main(String[] args)
	{
		int possibleCombinations = 0;
		int number = 9;

		do
		{
			number++;
			possibleCombinations = countPossibleCombinations(number, getAllPrimesSmallerThan(number));
		}
		while (possibleCombinations < 5000);

		System.out.println(number);
	}



	private static int countPossibleCombinations(int number, List<Integer> summands)
	{
		if (number == 0)
			return 1;
		else if (number < 0 || summands.isEmpty())
			return 0;
		else
		{
			int withFirstSummand = countPossibleCombinations(number - summands.get(0), summands);
			int withoutFirstSummand = countPossibleCombinations(number, createCopyWithoutFirstElement(summands));
			return withFirstSummand + withoutFirstSummand;
		}
	}



	private static List<Integer> createCopyWithoutFirstElement(List<Integer> original)
	{
		List<Integer> copy = new LinkedList<Integer>();
		for (int i = 1; i < original.size(); i++)
		{
			copy.add(original.get(i));
		}
		return copy;
	}



	private static List<Integer> getAllPrimesSmallerThan(int number)
	{
		List<Integer> primes = new LinkedList<Integer>();

		if (number >= 2)
		{
			primes.add(2);
			for (int i = 3; i <= number; i += 2)
			{
				if (IntegerUtils.isPrim(i))
				{
					primes.add(i);
				}
			}
		}

		return primes;
	}
}
