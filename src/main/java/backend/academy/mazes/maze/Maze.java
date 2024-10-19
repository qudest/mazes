package backend.academy.mazes.maze;

import backend.academy.mazes.maze.coordinate.Coordinate;
import java.util.Arrays;

public final class Maze {

    private final Cell[][] grid;

    public Cell[][] getGrid() {
        Cell[][] gridCopy = Arrays.copyOf(this.grid, this.grid.length);
        for (int i = 0; i < gridCopy.length; i++) {
            gridCopy[i] = Arrays.copyOf(this.grid[i], this.grid[i].length);
        }
        return gridCopy;
    }

    public Maze(Cell[][] grid) {
        this.grid = grid;
    }

    public boolean isWall(Coordinate coordinate) {
        return grid[coordinate.y()][coordinate.x()].type() == Cell.Type.WALL;
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }

}
