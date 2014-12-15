package problem;

import java.util.Collection;

import utils.IntegerUtils;

public class PermutedMultiples
{
	public static void main(String[] args)
	{
		int x = 1;
		while (true)
		{
			Collection<Integer> permutations = IntegerUtils.getPermutations(x);

			boolean containsAllMultiples = true;
			containsAllMultiples &= permutations.contains(x);
			containsAllMultiples &= permutations.contains(2 * x);
			containsAllMultiples &= permutations.contains(3 * x);
			containsAllMultiples &= permutations.contains(4 * x);
			containsAllMultiples &= permutations.contains(5 * x);
			containsAllMultiples &= permutations.contains(6 * x);

			if (containsAllMultiples)
			{
				break;
			}

			x++;
		}

		System.out.println(x);
	}

}
