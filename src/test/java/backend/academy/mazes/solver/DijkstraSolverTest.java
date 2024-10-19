package backend.academy.mazes.solver;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.output.MazeRenderer;
import backend.academy.mazes.output.Renderer;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DijkstraSolverTest {

    private final Renderer renderer = new MazeRenderer();
    private final Solver solver = new DijkstraSolver();

    @Test
    void solve() {
        Cell[][] grid = getGridWithSurfaces();
        Maze maze = new Maze(grid);
        System.out.println(renderer.render(maze));
        Coordinate start = new Coordinate(1, 1);
        Coordinate end = new Coordinate(5, 1);
        List<Coordinate> path = solver.solve(maze, start, end);
        System.out.println(renderer.render(maze, path));
        assertEquals(path, List.of(
            new Coordinate(1, 1),
            new Coordinate(1, 2),
            new Coordinate(1, 3),
            new Coordinate(2, 3),
            new Coordinate(3, 3),
            new Coordinate(4, 3),
            new Coordinate(5, 3),
            new Coordinate(5, 2),
            new Coordinate(5, 1)
        ));
    }

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

    private Cell[][] getGridWithSurfaces() {
        return new Cell[][] {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.BOOST),
                new Cell(Cell.Type.BOOST), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.DELAY), new Cell(Cell.Type.WALL), new Cell(Cell.Type.BOOST),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.DELAY), new Cell(Cell.Type.WALL), new Cell(Cell.Type.BOOST),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.DELAY), new Cell(Cell.Type.WALL), new Cell(Cell.Type.BOOST),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.BOOST),
                new Cell(Cell.Type.BOOST), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)}
        };
    }

    private Cell[][] getGridWithPass() {
        return new Cell[][] {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.PASSAGE),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)},
        };
    }

    private Cell[][] getGridWithoutPass() {
        return new Cell[][] {
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL)},
            {new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL),
                new Cell(Cell.Type.WALL)},
        };
    }
}
