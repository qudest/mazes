package backend.academy.mazes.solver;

import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.coordinate.Shift;
import java.util.ArrayList;
import java.util.List;

public class DFSSolver implements Solver {

    private final List<Shift> directions = List.of(
        new Shift(0, 1),
        new Shift(1, 0),
        new Shift(0, -1),
        new Shift(-1, 0)
    );

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        List<Coordinate> path = new ArrayList<>();
        List<Coordinate> visited = new ArrayList<>();
        if (search(maze, start, end, path, visited)) {
            return path;
        }
        return List.of();
    }

    private boolean search(Maze maze, Coordinate from, Coordinate to, List<Coordinate> path, List<Coordinate> visited) {
        if (maze.isWall(from) || visited.contains(from)) {
            return false;
        }

        path.add(from);
        visited.add(from);

        if (from.equals(to)) {
            return true;
        }

        for (Shift direction : directions) {
            Coordinate shiftedCoordinate = direction.getShiftedCoordinate(from);
            if (search(maze, shiftedCoordinate, to, path, visited)) {
                return true;
            }
        }

        path.removeLast();
        return false;
    }

    @Override public String toString() {
        return "Depth-First Search, DFS (не учитывает поверхности)";
    }

}
