package problem79;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class PasscodeDerivation
{

	public static void main(String[] args)
	{
		List<String> keylog = parseKeylog();

		Set<Character> occIndex0 = getOccurences(keylog, 0);
		Set<Character> occIndex1 = getOccurences(keylog, 1);
		Set<Character> occIndex2 = getOccurences(keylog, 2);

		Collection<Character> usedCharacters = new HashSet<Character>();
		usedCharacters.addAll(occIndex0);
		usedCharacters.addAll(occIndex1);
		usedCharacters.addAll(occIndex2);
	
		Set<Character> firstChars = new HashSet<Character>();
		for(char c : usedCharacters)
		{
			if (oc)
		}

	}



	private static List<String> parseKeylog()
	{
		InputStream inStream = PasscodeDerivation.class.getClassLoader().getResourceAsStream("problem79.txt");

		List<String> result = new LinkedList<String>();

		Scanner scanner = new Scanner(inStream);
		while (scanner.hasNext())
		{
			result.add(scanner.next());
		}

		return result;
	}



	private static Set<Character> getOccurences(List<String> keylog, int index)
	{
		Set<Character> result = new HashSet<Character>();

		for (String s : keylog)
		{
			char c = s.charAt(index);
			result.add(c);
		}

		return result;
	}
}
