package ae.test.sudokusolver;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {

    private Field[][] fields;
    private int dimension;
    private int boxDimension;

    public Sudoku(Field[][] fields) {
        this.fields = fields;
        this.dimension = fields.length;
        this.boxDimension = (int) Math.sqrt(dimension);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < getDimension(); y += 1) {
            for (int x = 0; x < getDimension(); x += 1) {
                sb.append(fields[x][y]);
                sb.append(" ");
                if (x % boxDimension == (boxDimension - 1)) {
                    sb.append(" ");
                }
            }
            if (y % boxDimension == (boxDimension - 1)) {
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public Field getField(int x, int y) {
        return fields[x][y];
    }

    // TODO create and return separate object?
    public List<Field> getRow(int y) {
        List<Field> row = new ArrayList<Field>(getDimension());
        for (int x = 0; x < getDimension(); x += 1) {
            row.add(getField(x, y));
        }
        return row;
    }

    public List<Field> getColumn(int x) {
        List<Field> column = new ArrayList<Field>(getDimension());
        for (int y = 0; y < getDimension(); y += 1) {
            column.add(getField(x, y));
        }
        return column;
    }

    public List<Field> getBox(int x, int y) {
        int boxRowOffset = (x / boxDimension) * boxDimension;
        int boxColOffset = (y / boxDimension) * boxDimension;

        List<Field> box = new ArrayList<Field>(getDimension());
        for (int i = 0; i < boxDimension; i += 1) {
            for (int j = 0; j < boxDimension; j += 1) {
                box.add(getField(boxRowOffset + i, boxColOffset + j));
            }
        }
        return box;
    }

    // TODO keep this method? how to initialize value?
    public int getDimension() {
        return fields.length;
    }
}
