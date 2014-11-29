package ae.test.sudokusolver.solver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ae.test.sudokusolver.Sudoku;
import ae.test.sudokusolver.SudokuBuilder;

public class EliminationSolverTest {

    private static Sudoku SOLVED;
    private static Sudoku INCOMPLETE;
    private static Sudoku INVALID;

    private EliminationSolver solver;

    @BeforeClass
    public static void setUpClass() throws Exception {

        SudokuBuilder builder = new SudokuBuilder(4);
        builder.initField(0, 0, 1);
        builder.initField(1, 0, 2);
        builder.initField(2, 0, 3);
        builder.initField(3, 0, 4);

        builder.initField(0, 1, 3);
        builder.initField(1, 1, 4);
        builder.initField(2, 1, 1);
        builder.initField(3, 1, 2);

        builder.initField(0, 2, 2);
        builder.initField(1, 2, 3);
        builder.initField(2, 2, 4);
        builder.initField(3, 2, 1);

        INCOMPLETE = builder.build();

        builder.initField(0, 3, 4);
        builder.initField(1, 3, 1);
        builder.initField(2, 3, 2);
        builder.initField(3, 3, 3);

        SOLVED = builder.build();

        builder.initField(0, 3, 4);
        builder.initField(1, 3, 4);
        builder.initField(2, 3, 4);
        builder.initField(3, 3, 4);

        INVALID = builder.build();
    }

    @Before
    public void setUp() {
        this.solver = new EliminationSolver();
    }

    @Test
    public void isSolvedReturnsTrueForSolvedSudoku() {
        assertTrue(solver.isSolved(SOLVED));
    }

    @Test
    public void isSolvedReturnsFalseForIncompleteSudoku() {
        assertFalse(solver.isSolved(SOLVED));
    }

    @Test
    public void isSolvedReturnsFalseForInvalidSudoku() {
        assertFalse(solver.isSolved(SOLVED));
    }

}
