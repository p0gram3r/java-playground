package problem77;

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

			possibleCombinations = countPossibleCombinations(number, getAllPrimesSmallerThan(number), 0);

			System.out.println(getAllPrimesSmallerThan(number));

			System.out.println(number + " => " + possibleCombinations);

		}
		while (possibleCombinations < 5000);

		System.out.println(number);
	}



	private static int countPossibleCombinations(int number, List<Integer> summands, int checkFromIndex)
	{
		if (number == 0)
			return 1;
		else if (number < 0 || summands.size() == checkFromIndex)
			return 0;
		else
		{
			int withFirstSummand = countPossibleCombinations(number - summands.get(checkFromIndex), summands, checkFromIndex);
			int withoutFirstSummand = countPossibleCombinations(number, summands, checkFromIndex + 1);
			return withFirstSummand + withoutFirstSummand;
		}
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
