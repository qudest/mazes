package backend.academy.mazes.generator;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RecursiveBacktrackerGenerator implements Generator {

    private final List<Direction> directions = Arrays.asList(Direction.values());

    @Override
    public Maze generate(int height, int width) {
        return generate(height, width, new Coordinate(1, 1));
    }

    @Override
    public Maze generate(int height, int width, Coordinate start) {
        Cell[][] grid = getFilledGrid(height, width);

        int yStart = start.row();
        int xStart = start.col();
        grid[yStart][xStart] = new Cell(yStart, xStart, Cell.Type.PASSAGE);

        carvePassagesFrom(grid, start);

        return new Maze(grid);
    }

    private Cell[][] getFilledGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(row, col, Cell.Type.WALL);
            }
        }
        return grid;
    }

    private void carvePassagesFrom(Cell[][] grid, Coordinate from) {
        Collections.shuffle(directions);
        for (Direction direction : directions) {
            int y = from.row() + direction.yShift;
            int x = from.col() + direction.xShift;
            Coordinate to = new Coordinate(y, x);
            if (isAvailableForMove(grid, to)) {
                int yBetween = from.row() + direction.yShift / 2;
                int xBetween = from.col() + direction.xShift / 2;
                grid[yBetween][xBetween] = new Cell(yBetween, xBetween, Cell.Type.PASSAGE);
                grid[y][x] = new Cell(y, x, Cell.Type.PASSAGE);
                carvePassagesFrom(grid, to);
            }
        }
    }

    private boolean isAvailableForMove(Cell[][] grid, Coordinate to) {
        int y = to.row();
        int x = to.col();
        return (y > 0 && y < grid.length - 1 && x > 0 && x < grid[0].length - 1 &&
            grid[y][x].type().equals(Cell.Type.WALL));
    }

    enum Direction {
        RIGHT(0, 2),
        DOWN(2, 0),
        LEFT(0, -2),
        UP(-2, 0);

        private final int yShift;
        private final int xShift;

        Direction(int yShift, int xShift) {
            this.yShift = yShift;
            this.xShift = xShift;
        }
    }

}
