package problem016;

import java.math.BigInteger;

public class P016_PowerDigitSum
{

	public static void main(String[] args)
	{
		BigInteger base = new BigInteger("2");
		int exponent = 1000;

		BigInteger result = base.pow(exponent);
		long sumOfDigits = sumDigits(result);

		System.out.println(sumOfDigits);
	}



	private static long sumDigits(BigInteger number)
	{
		long sumOfDigits = 0;

		String s = number.toString();
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(s.length() - i - 1);
			sumOfDigits += Long.valueOf(new StringBuilder().append(c).toString());
		}

		return sumOfDigits;
	}
}
