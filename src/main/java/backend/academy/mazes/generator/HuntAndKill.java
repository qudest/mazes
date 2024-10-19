package backend.academy.mazes.generator;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.coordinate.Shift;
import backend.academy.mazes.utils.GridUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuntAndKill implements Generator {

    private final List<Shift> bigShifts = new ArrayList<>(List.of(
        new Shift(0, 2),
        new Shift(2, 0),
        new Shift(0, -2),
        new Shift(-2, 0)
    ));

    private final List<Shift> smallShifts = new ArrayList<>(List.of(
        new Shift(0, 1),
        new Shift(0, -1),
        new Shift(1, 0),
        new Shift(-1, 0),
        new Shift(-1, 1),
        new Shift(-1, -1),
        new Shift(1, 1),
        new Shift(1, -1)
    ));

    @Override
    public Maze generate(int height, int width) {
        return generate(height, width, new Coordinate(1, 1));
    }

    @Override
    public Maze generate(int height, int width, Coordinate start) {
        Cell[][] grid = GridUtils.getFilledGrid(height, width);

        int yStart = start.y();
        int xStart = start.x();
        grid[yStart][xStart] = new Cell(Cell.Type.PASSAGE);

        Coordinate current = start;

        while (current != null) {
            kill(grid, current);
            current = hunt(grid);
        }

        return new Maze(grid);
    }

    private void kill(Cell[][] grid, Coordinate start) {
        Coordinate current = start;
        while (current != null) {
            Collections.shuffle(bigShifts);
            boolean isMoved = false;
            for (Shift shift : bigShifts) {
                Coordinate to = shift.getShiftedCoordinate(current);
                if (GridUtils.isAvailableForCarve(grid, to)) {
                    GridUtils.removeWallBetween(grid, current, to);
                    grid[to.y()][to.x()] = new Cell(Cell.getRandomPassage());
                    current = to;
                    isMoved = true;
                    break;
                }
            }
            if (!isMoved) {
                current = null;
            }
        }
    }

    private Coordinate hunt(Cell[][] grid) {
        for (int y = 1; y < grid.length - 1; y += 1) {
            for (int x = 1; x < grid[y].length - 1; x += 1) {
                if (grid[y][x].type() == Cell.Type.WALL) {
                    boolean flag = true;
                    for (Shift shift : smallShifts) {
                        Coordinate to = shift.getShiftedCoordinate(new Coordinate(y, x));
                        if (GridUtils.isAvailableForMove(grid, to)) {
                            flag = false;
                        }
                    }
                    if (flag) {
                        grid[y][x] = new Cell(Cell.Type.PASSAGE);
                        Collections.shuffle(bigShifts);
                        for (Shift shift : bigShifts) {
                            Coordinate to = shift.getShiftedCoordinate(new Coordinate(y, x));
                            if (GridUtils.isAvailableForMove(grid, to)) {
                                GridUtils.removeWallBetween(grid, new Coordinate(y, x), to);
                                break;
                            }
                        }
                        return new Coordinate(y, x);
                    }
                }
            }
        }
        return null;
    }

    @Override public String toString() {
        return "Hunt and Kill";
    }
}
