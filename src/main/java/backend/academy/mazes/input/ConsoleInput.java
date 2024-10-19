package backend.academy.mazes.input;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.Size;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.solver.Solver;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ConsoleInput implements Input {

    private final BufferedReader bufferedReader;
    private static final String INVALID_INPUT = "Некорректный ввод";
    private static final String INVALID_SIZE = "Некорректный размер";
    private static final String INVALID_COORDINATE = "Некорректные координаты";
    private static final String INVALID_COORDINATE_WALL = INVALID_COORDINATE + " (стена)";

    public ConsoleInput(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public Size inputSize() throws IOException {
        int[] size = inputTwoIntArguments();
        if (size[0] < Size.MIN_SIZE || size[1] < Size.MIN_SIZE || size[0] > Size.MAX_SIZE || size[1] > Size.MAX_SIZE) {
            throw new IOException(INVALID_SIZE);
        }
        return new Size(size[0], size[1]);
    }

    @Override
    public Coordinate inputCoordinate(Maze maze) throws IOException {
        int[] arguments = inputTwoIntArguments();
        if (arguments[0] < 0 || arguments[1] < 0
            || arguments[0] >= maze.getHeight() || arguments[1] >= maze.getWidth()) {
            throw new IOException(INVALID_COORDINATE);
        }
        Coordinate coordinate = new Coordinate(arguments[0], arguments[1]);
        if (maze.isWall(coordinate)) {
            throw new IOException(INVALID_COORDINATE_WALL);
        }
        return coordinate;
    }

    @Override
    public Generator inputGenerator(List<Generator> generators) throws IOException {
        try {
            int item = parseInt(bufferedReader.readLine());
            return generators.get(item - 1);
        } catch (Exception e) {
            throw new IOException(INVALID_INPUT, e);
        }
    }

    @Override
    public Solver inputSolver(List<Solver> solvers) throws IOException {
        try {
            int item = parseInt(bufferedReader.readLine());
            return solvers.get(item - 1);
        } catch (Exception e) {
            throw new IOException(INVALID_INPUT, e);
        }
    }

    private int parseInt(String line) throws IOException {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new IOException(INVALID_INPUT, e);
        }
    }

    private int[] inputTwoIntArguments() throws IOException {
        String line = bufferedReader.readLine();
        String[] parts = line.split(" ");
        if (parts.length != 2) {
            throw new IOException(INVALID_INPUT);
        }
        return new int[] {parseInt(parts[0]), parseInt(parts[1])};
    }
}
