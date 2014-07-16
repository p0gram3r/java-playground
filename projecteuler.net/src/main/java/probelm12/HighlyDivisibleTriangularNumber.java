package probelm12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class HighlyDivisibleTriangularNumber
{
	public static void main(String[] args)
	{
		int i = 1;
		int divisorCount = 0;

		do
		{
			long number = calculateTriangleNumber(i);

			Collection<Long> allDivisors = getAllDivisors(number);

			divisorCount = allDivisors.size();
			System.out.println(i + " -> " + number + ": " + divisorCount);

			i = i + 1;
		}
		while (divisorCount < 500);

		// long number = 54197677995L;
		// Collection<Long> allDivisors = getAllDivisors(number);
		// System.out.println(allDivisors);
		// System.out.println(allDivisors.size());
	}



	private static long calculateTriangleNumber(long n)
	{
		return n * (n + 1) / 2;
	}



	private static int countDivisiors(long number)
	{
		// all numbers can be divided by themselves
		int count = 1;

		Collection<Long> divs = new LinkedList<Long>();

		for (long i = 1; i * 2 <= number; i++)
		{
			if (number % i == 0)
			{
				count++;
				divs.add(i);
			}
		}
		System.out.println(divs);

		return count;
	}



	private static List<Long> getPrimFactors(long number)
	{
		List<Long> factors = new ArrayList<Long>();
		for (long i = 2; i * i <= number; i++)
		{
			while (number % i == 0)
			{
				factors.add(i);
				number /= i;
			}
		}
		if (number > 1)
		{
			factors.add(number);
		}
		return factors;
	}



	private static Collection<Long> getAllDivisors(long number)
	{
		List<Long> primFactors = getPrimFactors(number);

		Collection<Long> allDivisors = new TreeSet<Long>();
		allDivisors.add(1L);
		allDivisors.addAll(multiples(primFactors));

		return allDivisors;
	}



	private static List<Long> multiples(List<Long> $list)
	{
		if ($list.size() <= 1)
		{
			return $list;
		}

		long $x = $list.remove(0);

		List<Long> $res = new LinkedList<Long>();
		$res.add($x);

		List<Long> $sub = multiples($list);

		for (long $item : $sub)
		{
			$res.add($item);
			$res.add($x * $item);
		}

		return $res;
	}
}
