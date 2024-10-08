package backend.academy.mazes.maze;

import java.util.Arrays;

public final class Maze {

    private final int height;
    private final int width;
    private final Cell[][] grid;

    public Cell[][] getGrid() {
        Cell[][] grid = Arrays.copyOf(this.grid, this.grid.length);
        for (int i = 0; i < grid.length; i++) {
            grid[i] = Arrays.copyOf(this.grid[i], this.grid[i].length);
        }
        return grid;
    }

    public Maze(Cell[][] grid) {
        this.height = grid.length;
        this.width = grid[0].length;
        this.grid = grid;
    }

    public boolean isWall(Coordinate coordinate) {
        return grid[coordinate.y()][coordinate.x()].type().equals(Cell.Type.WALL);
    }

}
