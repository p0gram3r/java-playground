package ae.test.sudokusolver;

import ae.test.sudokusolver.Puzzle.PuzzleBuilder;

public class SudokuSolverMain {

    public static void main(String[] args) {
        PuzzleBuilder builder = new PuzzleBuilder();

        // upper left
        builder.initField(0, 8, 6);
        builder.initField(2, 8, 3);
        builder.initField(1, 7, 8);
        builder.initField(2, 7, 7);
        builder.initField(0, 6, 5);

        // upper center
        builder.initField(4, 7, 2);

        // upper right
        builder.initField(7, 6, 1);

        // mid left
        builder.initField(0, 5, 4);
        builder.initField(2, 3, 1);

        // mid center
        builder.initField(3, 4, 8);
        builder.initField(3, 3, 9);

        // mid right
        builder.initField(7, 5, 5);
        builder.initField(7, 4, 7);
        builder.initField(8, 3, 3);

        // lower left
        builder.initField(0, 2, 1);
        builder.initField(1, 2, 6);
        builder.initField(1, 1, 5);

        // lower center
        builder.initField(4, 1, 4);
        builder.initField(5, 1, 1);
        builder.initField(5, 0, 3);

        // lower right
        builder.initField(6, 2, 2);
        builder.initField(8, 0, 9);

        Puzzle puzzle = builder.buildPuzzle();
        System.out.println(puzzle);
    }
}
