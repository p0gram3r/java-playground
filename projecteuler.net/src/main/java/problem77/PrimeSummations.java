package problem77;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.TreeBag;

import utils.IntegerUtils;

// algorithm works for 500, but not 5000 :-(
public class PrimeSummations
{
	public static void main(String[] args)
	{
		System.out.println("not yet solved!");
		System.exit(1);

		int number = 2;
		int actualSummations;
		int expectedSummations = 500;

		do
		{
			number += 1;
			Set<Bag<Integer>> possibleSummations = getPossibleSummations(number);
			actualSummations = possibleSummations.size();
		}
		while (actualSummations < expectedSummations);

		System.out.println(number);
	}



	private static Set<Bag<Integer>> getPossibleSummations(int number)
	{
		Set<Bag<Integer>> result = new LinkedHashSet<Bag<Integer>>();

		for (int i = number; i > 1; i--)
		{
			if (IntegerUtils.isPrim(i))
			{
				int rest = number - i;
				if (rest == 0)
				{
					Bag<Integer> sublist = new TreeBag<Integer>();
					sublist.add(i);
					result.add(sublist);
				}
				else if (rest > 1)
				{
					for (Bag<Integer> subList : getPossibleSummations(rest))
					{
						subList.add(i);
						result.add(subList);
					}
				}
			}
		}

		return result;
	}

}
