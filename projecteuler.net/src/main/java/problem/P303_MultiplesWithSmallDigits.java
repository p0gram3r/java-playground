package problem;

import com.google.common.collect.ImmutableSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * For a positive integer n, define f(n) as the least positive multiple of n that, written in base 10, uses only
 * digits ≤ 2.
 * <p>
 * Thus f(2)=2, f(3)=12, f(7)=21, f(42)=210, f(89)=1121222.
 * <p>
 * Also, sum(1,100, f(n)/n) = 11363107
 * <p>
 * Find sum(1,10000, f(n)/n)
 */
public class P303_MultiplesWithSmallDigits implements Problem {

    private static String F_REGEX = "^[012]*$";

    @Override
    public Object getSolution() {

        try {
            File file = new File("multiples_of_n.txt");

            Set<String> values = ImmutableSet.of("0", "1", "2");
            Collection<String> perms = createPermutations(values, values, 1, 12);
            writeToTempFile(perms, file);

            int nCount = 100;

            Map<Long, Long> mapping = getMapping(file, nCount);
            if (mapping.size() != nCount) {
                throw new RuntimeException("mapping does not contain all necessary values!");
            }

            long result = 0;
            for (Long m : mapping.values()) {
                result += m;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    Map<Long, Long> getMapping(File fileWithMultiples, int nCount) throws FileNotFoundException {
        Map<Long, Long> mapping = new TreeMap<>();

        Scanner scanner = new Scanner(fileWithMultiples);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            // avoid useless computation
            if (mapping.size() == nCount - 2)
            {
                break;
            }

            // ignore illegal values
            if (!line.matches(F_REGEX)) {
                System.err.println("line does not match regex: " + line);
                continue;
            }

            // 0 is trivial and can be ignored
            Long multiple = Long.valueOf(line);
            if (multiple == 0) {
                continue;
            }

            for (long n = nCount; n > 2; n--) {
                long m = multiple / n;
                if (n * m == multiple) {
                    Long oldM = mapping.get(n);
                    if (oldM == null || m < oldM) {
                        mapping.put(n, m);
                    }
                }
            }
        }

        // add trivial values
        mapping.put(1L, 1L);
        mapping.put(2L, 1L);

        return mapping;
    }

    void writeToTempFile(Collection<String> data, File file) throws IOException {
        PrintWriter w = new PrintWriter(file);
        for (String p : data) {
            w.println(p);
        }
        w.flush();
    }

    Collection<String> createPermutations(Set<String> allPrefixes, Set<String> values, int count, int maxCount) {
        if (count >= maxCount) {
            return allPrefixes;
        }

        Set<String> newPrefixes = new LinkedHashSet<>();
        newPrefixes.addAll(allPrefixes);
        for (String p : allPrefixes) {
            for (String v : values) {
                newPrefixes.add("" + p + v);
            }
        }
        return (createPermutations(newPrefixes, values, count + 1, maxCount));
    }

}
