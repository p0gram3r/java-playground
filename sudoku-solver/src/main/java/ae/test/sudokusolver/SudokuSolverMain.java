package ae.test.sudokusolver;

import ae.test.sudokusolver.Puzzle.PuzzleBuilder;

public class SudokuSolverMain {

    public static void main(String[] args) {
        PuzzleBuilder builder = new PuzzleBuilder();
        builder.initField(0, 8, 6);
        builder.initField(2, 8, 3);
        builder.initField(1, 7, 8);
        builder.initField(2, 7, 7);

        Puzzle puzzle = builder.buildPuzzle();

        System.out.println(puzzle);
    }
}
