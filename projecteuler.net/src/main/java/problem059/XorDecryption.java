package problem059;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class XorDecryption
{
	public static void main(String[] args) throws FileNotFoundException
	{
		// PrintStream target = new PrintStream(new
		// File("problem59_output.txt"));

		// String[] commonWords = { "of", "the" };
		int[] message = getEncryptedMessage();

		// char key_char_start = 'a';
		// char key_char_end = 'z';
		// for (char i = key_char_start; i <= key_char_end; i++)
		// {
		// for (char j = key_char_start; j <= key_char_end; j++)
		// {
		// for (char k = key_char_start; k <= key_char_end; k++)
		// {
		// int[] key = { i, j, k };
		// int[] decrytptedText = applyKey(message, key);
		// String plainText = convertToString(decrytptedText);
		//
		// for (String word : commonWords)
		// {
		// if (plainText.contains(word))
		// {
		// target.println("---");
		// target.println("key: " + convertToString(key));
		// target.println(plainText);
		// break;
		// }
		// }
		// }
		// }
		// }

		int[] key = { 'g', 'o', 'd' };
		int[] decrytptedText = applyKey(message, key);

		int sum = 0;
		for (int i : decrytptedText)
		{
			sum += i;
		}
		System.out.println(sum);
	}



	private static int[] getEncryptedMessage()
	{
		InputStream inStream = XorDecryption.class.getClassLoader().getResourceAsStream("problem59.txt");

		Scanner s = new Scanner(inStream);
		String text = s.next();

		String[] letters = text.split(",");

		int[] result = new int[letters.length];
		for (int i = 0; i < letters.length; i++)
		{
			result[i] = Integer.parseInt(letters[i]);
		}

		return result;
	}



	private static int[] applyKey(int[] message, int[] key)
	{
		// expand the key to have the same length as the message
		// technically this is not necessary, just for readability
		int[] keyToApply = new int[message.length];
		for (int i = 0; i < message.length; i++)
		{
			keyToApply[i] = key[i % key.length];
		}

		int[] result = new int[message.length];
		for (int i = 0; i < message.length; i++)
		{
			// TODO use key directly
			result[i] = message[i] ^ keyToApply[i];
		}

		return result;
	}
}
