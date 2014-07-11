

import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashSet;

public class IntegerUtils
{

	public static boolean isPrim(int number)
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



	public static Collection<Integer> getPermutations(int number)
	{
		int[] num = convertIntegerToArray(number);

		Collection<Integer> result = new LinkedHashSet<Integer>();
		permute(num, 0, result);
		return result;
	}



	private static void permute(int[] num, int start, Collection<Integer> result)
	{
		if (start >= num.length)
		{
			Integer item = convertArrayToInteger(num);
			// only consider numbers greater than the original one
			if (item >= BigInteger.TEN.pow(num.length - 1).intValue())
			{
				result.add(item);
			}
		}

		for (int j = start; j < num.length; j++)
		{
			swap(num, start, j);
			permute(num, start + 1, result);
			swap(num, start, j);
		}
	}



	private static int[] convertIntegerToArray(int number)
	{
		int arrayLen = Integer.toString(number).length();
		int[] result = new int[arrayLen];
		for (int i = 0; i < arrayLen; i++)
		{
			result[arrayLen - i - 1] = number % 10;
			number = number / 10;
		}
		return result;
	}



	private static Integer convertArrayToInteger(int[] num)
	{
		Integer item = 0;
		for (int i = 0; i < num.length; i++)
		{
			int index = num.length - i - 1;
			int multiplier = BigInteger.TEN.pow(i).intValue();
			item += num[index] * multiplier;
		}
		return item;
	}



	private static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
