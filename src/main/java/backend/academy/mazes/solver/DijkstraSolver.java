package backend.academy.mazes.solver;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.coordinate.Shift;
import backend.academy.mazes.utils.GridUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraSolver implements Solver {

    private final List<Shift> directions = List.of(
        new Shift(0, 1),
        new Shift(1, 0),
        new Shift(0, -1),
        new Shift(-1, 0)
    );

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        int height = maze.getHeight();
        int width = maze.getWidth();
        Cell[][] grid = maze.getGrid();

        int[][] dist = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        Coordinate[][] prev = new Coordinate[height][width];

        PriorityQueue<CellInfo> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.distance));

        dist[start.y()][start.x()] = 0;
        pq.add(new CellInfo(start, 0));

        while (!pq.isEmpty()) {
            CellInfo current = pq.poll();
            Coordinate currentCoordinate = current.coordinate;

            if (currentCoordinate.equals(end)) {
                break;
            }

            for (Shift direction : directions) {
                Coordinate neighbor = direction.getShiftedCoordinate(currentCoordinate);

                if (GridUtils.isAvailableForMove(grid, neighbor)) {
                    int newDist = dist[currentCoordinate.y()][currentCoordinate.x()] +
                        grid[neighbor.y()][neighbor.x()].getWeight();

                    if (newDist < dist[neighbor.y()][neighbor.x()]) {
                        dist[neighbor.y()][neighbor.x()] = newDist;
                        prev[neighbor.y()][neighbor.x()] = currentCoordinate;
                        pq.add(new CellInfo(neighbor, newDist));
                    }
                }
            }
        }

        if (dist[end.y()][end.x()] == Integer.MAX_VALUE) {
            return List.of();
        }

        List<Coordinate> path = new ArrayList<>();
        Coordinate current = end;
        while (current != null) {
            path.add(current);
            current = prev[current.y()][current.x()];
        }

        Collections.reverse(path);

        return path;
    }

    private static class CellInfo {
        Coordinate coordinate;
        int distance;

        public CellInfo(Coordinate coordinate, int distance) {
            this.coordinate = coordinate;
            this.distance = distance;
        }
    }

    @Override public String toString() {
        return "Dijkstra's Algorithm (учитывает поверхности)";
    }
}
