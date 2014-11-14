package problem50;

import java.util.LinkedList;
import java.util.List;

import utils.IntegerUtils;

public class ConsecutivePrimeSum
{
	// 41 = 2 + 3 + 5 + 7 + 11 + 13
	public static void main(String[] args)
	{
		int limit = 1000000;

		// create a list of all primes up to limit
		List<Integer> primes = createListOfPrimesUpTo(limit);
		System.out.println(primes.size() + " prime numbers to check");

		List<Integer> asdf = new LinkedList<Integer>();

		for (int i = 0; i < primes.size() / 2; i += 1)
		{
			int sum = primes.get(i);

			List<Integer> temp = new LinkedList<Integer>();
			temp.add(primes.get(i));

			for (int j = i + 1; j < primes.size(); j += 1)
			{
				sum += primes.get(j);
				temp.add(primes.get(j));
				if (sum > limit)
				{
					break;
				}
				if (primes.contains(sum))
				{
					// System.out.println("   sum: " + sum + " --- " + temp);

					if (asdf.size() < temp.size())
					{
						asdf = new LinkedList<Integer>(temp);
					}
				}

			}
		}
		System.out.println();
		System.out.println();
		System.out.println(asdf);

		int sum = 0;
		for (int i : asdf)
		{
			sum += i;
		}
		System.out.println(" ==> " + sum);
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
