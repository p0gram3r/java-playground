package problem50;

import java.util.LinkedList;
import java.util.List;

import utils.IntegerUtils;

public class ConsecutivePrimeSum
{
	public static void main(String[] args)
	{
		int limit = 100;

		// create a list of all primes up to limit
		List<Integer> primes = createListOfPrimesUpTo(limit);

		// 41 = 2 + 3 + 5 + 7 + 11 + 13

		System.out.println(primes);

	}



	private static List<Integer> createListOfPrimesUpTo(int limit)
	{
		List<Integer> primes = new LinkedList<Integer>();
		primes.add(2);
		for (int i = 3; i < limit; i += 2)
		{
			if (IntegerUtils.isPrim(i))
			{
				primes.add(i);
			}
		}
		return primes;
	}

}
