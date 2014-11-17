package problem002;

public class EvenFibonacciNumbers
{
	public static void main(String[] args)
	{
		int limit = 4000000;
		long sum = 0;

		// F0 and F1
		long a = 0;
		long b = 1;

		while (b < limit)
		{
			long temp = b;
			b = a + b;
			a = temp;

			if (b % 2 == 0)
			{
				sum += b;
			}
		}

		System.out.println(sum);
	}
}
