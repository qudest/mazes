package backend.academy.mazes.generator;

import backend.academy.mazes.maze.Maze;

public interface Generator {

    Maze generate(int height, int width);

}
