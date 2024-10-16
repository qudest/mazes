package backend.academy.mazes.output;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.solver.Solver;
import java.util.List;

public interface Output {

    void displayStartScreen();

    void displaySizeSelection();

    void displayGeneratorSelection(List<Generator> generators);

    void displaySolverSelection(List<Solver> solvers);

    void displayException(String exceptionMessage);

    void displayMaze(String maze);

    void displayStartCoordinateSelection();

    void displayEndCoordinateSelection();

}
