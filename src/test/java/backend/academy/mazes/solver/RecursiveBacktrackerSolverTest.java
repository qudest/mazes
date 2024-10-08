package backend.academy.mazes.solver;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.output.MazeRenderer;
import backend.academy.mazes.output.Renderer;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecursiveBacktrackerSolverTest {

    private final Solver solver = new RecursiveBacktrackerSolver();
    private final Renderer renderer = new MazeRenderer();

    @Test
    void solveWithPass() {
        Cell[][] grid = getGridWithPass();
        Maze maze = new Maze(grid);
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        List<Coordinate> path = solver.solve(maze, start, end);
        System.out.println(renderer.render(maze, path));
        assertTrue(path.contains(end));
    }

    @Test
    void solveWithoutPass() {
        Cell[][] grid = getGridWithoutPass();
        Maze maze = new Maze(grid);
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(3, 3);
        List<Coordinate> path = solver.solve(maze, start, end);
        System.out.println(renderer.render(maze, path));
        assertFalse(path.contains(end));
    }

    private Cell[][] getGridWithPass() {
        return new Cell[][] {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
        };
    }

    private Cell[][] getGridWithoutPass() {
        return new Cell[][] {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL)},
        };
    }

}
