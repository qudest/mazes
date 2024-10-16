package backend.academy.mazes.input;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.Size;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.solver.Solver;
import java.io.IOException;
import java.util.List;

public interface Input {

    Size inputSize() throws IOException;

    Coordinate inputCoordinate(Maze maze) throws IOException;

    Generator inputGenerator(List<Generator> generators) throws IOException;

    Solver inputSolver(List<Solver> solvers) throws IOException;

}
