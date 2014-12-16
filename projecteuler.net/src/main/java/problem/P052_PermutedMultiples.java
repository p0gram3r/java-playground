package problem;

import java.util.Collection;

import utils.IntegerUtils;

public class P052_PermutedMultiples implements Problem {
    public Object getSolution() {
        int x = 1;
        while (true) {
            Collection<Integer> permutations = IntegerUtils.getPermutations(x);

            boolean containsAllMultiples = true;
            containsAllMultiples &= permutations.contains(x);
            containsAllMultiples &= permutations.contains(2 * x);
            containsAllMultiples &= permutations.contains(3 * x);
            containsAllMultiples &= permutations.contains(4 * x);
            containsAllMultiples &= permutations.contains(5 * x);
            containsAllMultiples &= permutations.contains(6 * x);

            if (containsAllMultiples) {
                break;
            }

            x++;
        }

        return x;
    }

}
