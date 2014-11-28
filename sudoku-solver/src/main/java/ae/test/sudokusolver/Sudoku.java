package ae.test.sudokusolver;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private Field[][] fields;

    public Sudoku(Field[][] fields) {
        this.fields = fields;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 9; y += 1) {
            for (int x = 0; x < 9; x += 1) {
                sb.append(fields[x][y]);
                sb.append(" ");
                if (x % 3 == 2) {
                    sb.append(" ");
                }
            }
            if (y % 3 == 2) {
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // TODO find faster and correct implementation
    public boolean isSolved() {
        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                if (fields[i][j].getValue() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public Field getField(int x, int y) {
        return fields[x][y];
    }

    // TODO create and return separate object?
    public List<Field> getRow(int y) {
        List<Field> row = new ArrayList<Field>(fields.length);
        for (int x = 0; x < fields.length; x += 1) {
            row.add(getField(x, y));
        }
        return row;
    }

    public List<Field> getColumn(int x) {
        List<Field> column = new ArrayList<Field>(fields.length);
        for (int y = 0; y < fields.length; y += 1) {
            column.add(getField(x, y));
        }
        return column;
    }

    public List<Field> getBox(int x, int y) {
        // FIXME this should not be hardcoded
        int boxRowOffset = (x / 3) * 3;
        int boxColOffset = (y / 3) * 3;

        List<Field> box = new ArrayList<Field>(fields.length);
        for (int i = 0; i < 3; i += 1) {
            for (int j = 0; j < 3; j += 1) {
                box.add(getField(boxRowOffset + i, boxColOffset + j));
            }
        }
        return box;
    }
}
