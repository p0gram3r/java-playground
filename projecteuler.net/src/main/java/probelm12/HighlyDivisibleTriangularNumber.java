package probelm12;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class HighlyDivisibleTriangularNumber
{
	public static void main(String[] args)
	{
		// int i = 1;
		// int divisorCount = 0;
		//
		// do
		// {
		// long number = calculateTriangleNumber(i);
		// List<Long> primFactors = getPrimFactors(number);
		//
		// Collection<Long> allDivisors = getAllDivisors(primFactors);
		//
		// divisorCount = allDivisors.size();
		// System.out.println(i + " -> " + number + ": " + divisorCount);
		//
		// i = i + 1;
		// }
		// while (divisorCount < 200);

		long number = 54197677995L;

		Collection<Long> allDivisors = getAllDivisors(number);
		System.out.println(allDivisors + " --> " + allDivisors.size());

		int countDivisors = countDivisiors(number);
		System.out.println(countDivisors);
	}



	private static long calculateTriangleNumber(long n)
	{
		return n * (n + 1) / 2;
	}



	private static int countDivisiors(long number)
	{
		// all numbers can be divided by themselves
		int count = 1;

		for (long i = 1; i * 2 <= number; i++)
		{
			if (number % i == 0)
			{
				count++;
				System.out.println(i);
			}
		}

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

		for (int i = 0; i < primFactors.size(); i++)
		{
			for (int j = i + 1; j < primFactors.size(); j++)
			{
				allDivisors.add(primFactors.get(i) * primFactors.get(j));
			}
		}

		allDivisors.add(1L);
		allDivisors.addAll(primFactors);

		return allDivisors;
	}
}
