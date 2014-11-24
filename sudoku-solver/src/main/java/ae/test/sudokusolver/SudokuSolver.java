package ae.test.sudokusolver;

public class SudokuSolver {

    private Field[][] fields;

    public SudokuSolver() {
        fields = new Field[9][9];

        for (int i = 0; i < 9; i += 1) {
            for (int j = 0; j < 9; j += 1) {
                fields[i][j] = new Field();
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 8; y >= 0; y -= 1) {
            for (int x = 0; x < 9; x += 1) {
                sb.append(fields[x][y]);
                sb.append(" ");
                if (x % 3 == 2) {
                    sb.append(" ");
                }
            }
            if (y % 3 == 0) {
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void initField(int x, int y, int value) {
        fields[x][y].setValue(value);
    }

}
