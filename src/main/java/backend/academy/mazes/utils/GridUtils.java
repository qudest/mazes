package backend.academy.mazes.utils;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.coordinate.Coordinate;

public final class GridUtils {

    public static Cell[][] getFilledGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(Cell.Type.WALL);
            }
        }
        return grid;
    }

    public static boolean isAvailableForCarve(Cell[][] grid, Coordinate to) {
        return isWithin(grid, to) && grid[to.y()][to.x()].type() == Cell.Type.WALL;
    }

    public static boolean isAvailableForMove(Cell[][] grid, Coordinate to) {
        return isWithin(grid, to) && grid[to.y()][to.x()].type() != Cell.Type.WALL;
    }

    private static boolean isWithin(Cell[][] grid, Coordinate to) {
        int y = to.y();
        int x = to.x();
        return (y > 0 && y < grid.length - 1 && x > 0 && x < grid[0].length - 1);
    }

    public static void removeWallBetween(Cell[][] grid, Coordinate from, Coordinate to) {
        int yBetween = (from.y() + to.y()) / 2;
        int xBetween = (from.x() + to.x()) / 2;
        grid[yBetween][xBetween] = new Cell(Cell.Type.PASSAGE);
    }

    private GridUtils() {
    }

}
