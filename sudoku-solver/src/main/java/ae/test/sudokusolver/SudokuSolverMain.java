package ae.test.sudokusolver;


public class SudokuSolverMain {

    public static void main(String[] args) {
        SudokuBuilder builder = new SudokuBuilder(9);

        // upper left
        builder.initField(0, 0, 6);
        builder.initField(2, 0, 3);
        builder.initField(1, 1, 8);
        builder.initField(2, 1, 7);
        builder.initField(0, 2, 5);

        // upper center
        builder.initField(4, 1, 2);

        // upper right
        builder.initField(7, 2, 1);

        // mid left
        builder.initField(0, 3, 4);
        builder.initField(2, 5, 1);

        // mid center
        builder.initField(3, 4, 8);
        builder.initField(3, 5, 9);

        // mid right
        builder.initField(7, 3, 5);
        builder.initField(7, 4, 7);
        builder.initField(8, 5, 3);

        // lower left
        builder.initField(0, 6, 1);
        builder.initField(1, 6, 6);
        builder.initField(1, 7, 5);

        // lower center
        builder.initField(4, 7, 4);
        builder.initField(5, 7, 1);
        builder.initField(5, 8, 3);

        // lower right
        builder.initField(6, 6, 2);
        builder.initField(8, 8, 9);

        Sudoku puzzle = builder.build();
        System.out.println(puzzle);
    }

}
