package problem050;

import java.util.LinkedList;
import java.util.List;

import utils.IntegerUtils;

public class ConsecutivePrimeSum
{
	// 41 = 2 + 3 + 5 + 7 + 11 + 13
	public static void main(String[] args)
	{
		int limit = 1000000;

		// create a list of all primes up to a specific limit. this should not
		// be the limit defined above, as in case of 1 million the list would
		// contain about 78000 prime numbers to check
		List<Integer> primes = createListOfPrimesUpTo(4000);
		System.out.println(primes.size() + " prime numbers to check");

		List<Integer> longestStreak = new LinkedList<Integer>();

		for (int i = 0; i < primes.size() / 2; i += 1)
		{
			int sum = 0;
			List<Integer> temp = new LinkedList<Integer>();

			for (int j = i; j < primes.size(); j += 1)
			{
				sum += primes.get(j);
				temp.add(primes.get(j));
				if (sum > limit)
				{
					break;
				}

				if (IntegerUtils.isPrim(sum) && longestStreak.size() < temp.size())
				{
					longestStreak = new LinkedList<Integer>(temp);
				}

			}
		}

		int sum = 0;
		for (int i : longestStreak)
		{
			sum += i;
		}
		System.out.println();
		System.out.println();
		System.out.println("sum: " + sum);
		System.out.println("  #: " + longestStreak.size());
		System.out.println();
		System.out.println(longestStreak);
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
