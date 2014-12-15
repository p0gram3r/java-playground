package problem;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;

public class LargeSum
{
	public static void main(String[] args)
	{
		BigInteger sum = BigInteger.ZERO;

		InputStream inStream = LargeSum.class.getClassLoader().getResourceAsStream("problem13.txt");
		Scanner scanner = new Scanner(inStream);

		while (scanner.hasNext())
		{
			String number = scanner.nextLine();
			sum = sum.add(new BigInteger(number));
		}

		System.out.println(sum);

		String s = sum.toString();
		System.out.println(s.length());
		System.out.println(s.substring(0, 10));
	}

}
