package ae.test.sudokusolver;

public class SudokuBuilder {
    private int dimension;
    private Field[][] fields;

    public SudokuBuilder(int dimension) {
        this.dimension = dimension;
        this.fields = new Field[dimension][dimension];
        for (int i = 0; i < dimension; i += 1) {
            for (int j = 0; j < dimension; j += 1) {
                fields[i][j] = new Field();
            }
        }
    }

    public SudokuBuilder initField(int x, int y, int value) {
        if (x < 0 || x >= dimension || y < 0 || y >= dimension) {
            throw new IllegalArgumentException();
        }
        fields[x][y].setValue(value);
        return this;
    }

    public Sudoku build() {
        return new Sudoku(fields);
    }
}