package backend.academy.mazes.solver;

import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.List;

public interface Solver {

    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);

}
