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
        List<Integer> size = inputTwoIntArguments();
        int height = size.getFirst();
        int width = size.getLast();
        if (height < Size.MIN_SIZE || width < Size.MIN_SIZE || height > Size.MAX_SIZE || width > Size.MAX_SIZE) {
            throw new IOException(INVALID_SIZE);
        }
        return new Size(height, width);
    }

    @Override
    public Coordinate inputCoordinate(Maze maze) throws IOException {
        List<Integer> arguments = inputTwoIntArguments();
        int y = arguments.getFirst();
        int x = arguments.getLast();
        if (y < 0 || x < 0
            || y >= maze.getHeight() || x >= maze.getWidth()) {
            throw new IOException(INVALID_COORDINATE);
        }
        Coordinate coordinate = new Coordinate(y, x);
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

    private List<Integer> inputTwoIntArguments() throws IOException {
        String line = bufferedReader.readLine();
        String[] parts = line.split(" ");
        if (parts.length != 2) {
            throw new IOException(INVALID_INPUT);
        }
        return List.of(parseInt(parts[0]), parseInt(parts[1]));
    }
}
