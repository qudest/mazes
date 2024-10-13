package backend.academy.mazes.maze;

import backend.academy.mazes.maze.coordinate.Coordinate;
import java.util.Arrays;

public final class Maze {

    private final Cell[][] grid;

    public Cell[][] getGrid() {
        Cell[][] grid = Arrays.copyOf(this.grid, this.grid.length);
        for (int i = 0; i < grid.length; i++) {
            grid[i] = Arrays.copyOf(this.grid[i], this.grid[i].length);
        }
        return grid;
    }

    public Maze(Cell[][] grid) {
        this.grid = grid;
    }

    public boolean isWall(Coordinate coordinate) {
        return grid[coordinate.y()][coordinate.x()].type().equals(Cell.Type.WALL);
    }

    public int getHeight() {
        return grid.length;
    }

    public int getWidth() {
        return grid[0].length;
    }

}
