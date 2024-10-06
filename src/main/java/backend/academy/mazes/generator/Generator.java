package backend.academy.mazes.generator;

import backend.academy.mazes.maze.Coordinate;
import backend.academy.mazes.maze.Maze;

public interface Generator {

    Maze generate(int height, int width);

    Maze generate(int height, int width, Coordinate start);

}
