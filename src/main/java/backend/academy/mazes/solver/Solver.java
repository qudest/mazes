package backend.academy.mazes.solver;

import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import java.util.List;

public interface Solver {

    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

}
