package ae.test.sudokusolver;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

public class Field {
    private Integer value = null;
    private SortedSet<Integer> possibleValues = new TreeSet<Integer>();

    public Field() {
        for (int i = 1; i <= 9; i += 1) {
            possibleValues.add(i);
        }
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;

        possibleValues.clear();
        possibleValues.add(value);
    }

    public SortedSet<Integer> getPossibleValues() {
        return Collections.unmodifiableSortedSet(possibleValues);
    }

    public void removePossibility(int possibility) {
        possibleValues.remove(possibility);
    }

    @Override
    public String toString() {
        return (value == null ? "_" : String.valueOf(value));
    }
}
