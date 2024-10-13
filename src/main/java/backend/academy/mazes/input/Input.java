package backend.academy.mazes.input;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.generator.GeneratorPool;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.Size;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.solver.Solver;
import backend.academy.mazes.solver.SolverPool;
import java.io.IOException;

public interface Input {

    Size inputSize() throws IOException;

    Coordinate inputCoordinate(Maze maze) throws IOException;

    Generator inputGenerator(GeneratorPool generatorPool) throws IOException;

    Solver inputSolver(SolverPool solverPool) throws IOException;

}
