package problem07;

import utils.IntegerUtils;

public class PrimeNumber10001
{
	public static void main(String[] args)
	{
		int currentNumber = 0;
		int index = 0;
		do
		{
			currentNumber++;

			if (IntegerUtils.isPrim(currentNumber))
			{
				index++;
				System.out.println("index " + index + ": " + currentNumber);
			}

		}
		while (index < 10001);
	}
}
