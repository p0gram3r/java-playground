package problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetsWithUniqueSum
{
	public static void main(String[] args)
	{
		// Consider the set B = {1,3,6,8,10,11}
		Set<Long> b = new HashSet<Long>(Arrays.asList(1L, 3L, 6L, 8L, 10L, 11L));

		System.out.println(powerSet(b));

		Set<Long> u = unique(b, 3);
		System.out.println(u);
		System.out.println(sum(u));
	}



	/**
	 * For a set A, let U(A,k) be the set of unique sums of k-element subsets of
	 * A, in our example we find U(B,3) = {10,12,14,18,21,25,27,29} and
	 * sum(U(B,3)) = 156.
	 * 
	 * @param A
	 *            the set
	 * @param k
	 *            number of elements in subsets
	 * @return
	 */
	private static Set<Long> unique(Set<Long> A, int k)
	{
		Set<Long> result = new HashSet<Long>();

		Set<Set<Long>> allSubSets = powerSet(A);
		for (Set<Long> s : allSubSets)
		{
			if (s.size() == k)
			{
				System.out.println(" adding " + s + " --> " + sum(s));
				result.add(sum(s));
			}
		}

		return result;
	}



	/**
	 * For any set A of numbers, let sum(A) be the sum of the elements of A
	 * 
	 * @param s
	 *            the set
	 * @return the sum of all elements
	 */
	private static long sum(Set<Long> s)
	{
		Long result = 0L;
		for (Long l : s)
		{
			result += l;
		}
		return result;
	}



	public static <T> Set<Set<T>> powerSet(Set<T> originalSet)
	{
		Set<Set<T>> sets = new HashSet<Set<T>>();
		if (originalSet.isEmpty())
		{
			sets.add(new HashSet<T>());
			return sets;
		}

		List<T> list = new ArrayList<T>(originalSet);
		T head = list.get(0);
		Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
		for (Set<T> set : powerSet(rest))
		{
			Set<T> newSet = new HashSet<T>();
			newSet.add(head);
			newSet.addAll(set);

			sets.add(newSet);
			sets.add(set);
		}
		return sets;
	}
}
