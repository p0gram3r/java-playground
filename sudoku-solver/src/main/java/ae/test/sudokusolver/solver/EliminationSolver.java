package ae.test.sudokusolver.solver;

import java.util.List;

import ae.test.sudokusolver.Field;
import ae.test.sudokusolver.Sudoku;

/**
 * Solver based on elimination algorithm created by Robert Kosten and p0gram3r.
 */
public class EliminationSolver {

    public void solve(Sudoku sudoku) {

        while (!isSolved(sudoku)) {

        }
    }

    public boolean isSolved(Sudoku sudoku) {
        int dimension = sudoku.getDimension();

        for (int i = 0; i < dimension; i += 1) {
            isValidFieldCollection(sudoku.getRow(i));
            isValidFieldCollection(sudoku.getColumn(i));
        }

        for (int i = 0; i < dimension; i += 1) {

        }

        return true;
    }

    private boolean isValidFieldCollection(List<Field> fields) {
        for (Field f : fields) {
            if (f.getValue() == null) {
                return false;
            }
        }
        return true;
    }
}
