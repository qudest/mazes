package backend.academy.mazes.output;

import backend.academy.mazes.maze.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.List;

public interface Renderer {

    String render(Maze maze);

    String render(Maze maze, List<Coordinate> path);

}
