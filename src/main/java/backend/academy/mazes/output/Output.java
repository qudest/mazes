package backend.academy.mazes.output;

import backend.academy.mazes.generator.GeneratorPool;
import backend.academy.mazes.solver.SolverPool;

public interface Output {

    void displayStartScreen();

    void displaySizeSelection();

    void displayGeneratorSelection(GeneratorPool generatorPool);

    void displaySolverSelection(SolverPool solverPool);

    void displayException(String exceptionMessage);

    void displayMaze(String maze);

    void displayStartCoordinateSelection();

    void displayEndCoordinateSelection();

}
