package problem79;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PasscodeDerivation
{

    public static void main(String[] args)
    {
        List<String> keylog = parseKeylog();

        Map<Character, Integer> occIndex0 = getOccurences(keylog, 0);
        Map<Character, Integer> occIndex1 = getOccurences(keylog, 1);
        Map<Character, Integer> occIndex2 = getOccurences(keylog, 2);

        System.out.println(occIndex0);
        System.out.println(occIndex1);
        System.out.println(occIndex2);
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



    private static Map<Character, Integer> getOccurences(List<String> keylog, int index)
    {
        Map<Character, Integer> result = new HashMap<Character, Integer>();

        for (String s : keylog)
        {
            char c = s.charAt(index);
            Integer i = result.get(c);
            if (i == null)
            {
                i = 0;
            }
            result.put(c, i + 1);
        }

        return result;
    }
}
