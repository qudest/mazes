package backend.academy.mazes.generator;

import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;

public interface Generator {

    Maze generate(int height, int width);

    Maze generate(int height, int width, Coordinate start);

}
