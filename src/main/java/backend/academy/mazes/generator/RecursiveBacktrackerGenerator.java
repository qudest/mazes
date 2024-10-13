package backend.academy.mazes.generator;

import backend.academy.mazes.maze.coordinate.Shift;
import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecursiveBacktrackerGenerator implements Generator {

    private final List<Shift> shifts = new ArrayList<>(List.of(
        new Shift(0, 2),
        new Shift(2, 0),
        new Shift(0, -2),
        new Shift(-2, 0)
    ));

    @Override
    public Maze generate(int height, int width) {
        return generate(height, width, new Coordinate(1, 1));
    }

    @Override
    public Maze generate(int height, int width, Coordinate start) {
        Cell[][] grid = getFilledGrid(height, width);

        int yStart = start.y();
        int xStart = start.x();
        grid[yStart][xStart] = new Cell(Cell.Type.PASSAGE);

        carvePassagesFrom(grid, start);

        return new Maze(grid);
    }

    private Cell[][] getFilledGrid(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                grid[row][col] = new Cell(Cell.Type.WALL);
            }
        }
        return grid;
    }

    private void carvePassagesFrom(Cell[][] grid, Coordinate from) {
        Collections.shuffle(shifts);
        for (Shift shift : shifts) {
            Coordinate to = shift.getShiftedCoordinate(from);
            if (isAvailableForMove(grid, to)) {
                int yBetween = from.y() + shift.y() / 2;
                int xBetween = from.x() + shift.x() / 2;
                grid[yBetween][xBetween] = new Cell(Cell.Type.PASSAGE);
                grid[to.y()][to.x()] = new Cell(Cell.Type.PASSAGE);
                carvePassagesFrom(grid, to);
            }
        }
    }

    private boolean isAvailableForMove(Cell[][] grid, Coordinate to) {
        int y = to.y();
        int x = to.x();
        return (y > 0 && y < grid.length - 1 && x > 0 && x < grid[0].length - 1 &&
            grid[y][x].type().equals(Cell.Type.WALL));
    }

    @Override public String toString() {
        return "Recursive Backtracker";
    }

}
