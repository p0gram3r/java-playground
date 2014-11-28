package ae.test.sudokusolver;

public class Sudoku {

    public static class SudokuBuilder {
        private Field[][] fields;

        public SudokuBuilder(int dimension) {
            // TODO add checks
            this.fields = new Field[dimension][dimension];
            for (int i = 0; i < 9; i += 1) {
                for (int j = 0; j < 9; j += 1) {
                    fields[i][j] = new Field();
                }
            }
        }

        public SudokuBuilder initField(int x, int y, int value) {
            fields[x][y].setValue(value);
            return this;
        }

        public Sudoku build() {
            return new Sudoku(fields);
        }
    }

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

    // TODO find faster implementation
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

}
