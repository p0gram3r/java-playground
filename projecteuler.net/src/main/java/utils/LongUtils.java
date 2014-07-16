package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class LongUtils
{

	public static boolean isPrim(long number)
	{
		if (number < 2)
		{
			return false;
		}

		if (number == 2)
		{
			return true;
		}

		if (number % 2 == 0)
		{
			return false;
		}

		for (int i = 3; i * i <= number; i = i + 2)
		{
			if (number % i == 0)
			{
				return false;
			}
		}

		return true;
	}



	public static List<Long> getPrimFactors(long number)
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



	public static Collection<Long> getAllDivisors(long number)
	{
		List<Long> primFactors = getPrimFactors(number);

		Collection<Long> allDivisors = new TreeSet<Long>();
		allDivisors.add(1L);
		allDivisors.addAll(multiples(primFactors));

		return allDivisors;
	}



	public static List<Long> multiples(List<Long> numberList)
	{
		if (numberList.size() <= 1)
		{
			return numberList;
		}

		long firstElement = numberList.remove(0);

		List<Long> result = new LinkedList<Long>();
		result.add(firstElement);

		List<Long> subList = multiples(numberList);

		for (long $item : subList)
		{
			result.add($item);
			result.add(firstElement * $item);
		}

		return result;
	}

}
