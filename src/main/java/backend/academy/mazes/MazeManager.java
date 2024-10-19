package backend.academy.mazes;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.input.Input;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.Size;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.output.Output;
import backend.academy.mazes.output.Renderer;
import backend.academy.mazes.solver.Solver;
import java.util.List;

public class MazeManager {

    private final Input input;
    private final Renderer renderer;
    private final Output output;
    private final List<Generator> generators;
    private final List<Solver> solvers;

    public MazeManager(
        Input input,
        Renderer renderer,
        Output output,
        List<Generator> generators,
        List<Solver> solvers
    ) {
        this.input = input;
        this.renderer = renderer;
        this.output = output;
        this.generators = generators;
        this.solvers = solvers;
    }

    public void start() {
        output.displayStartScreen();

        output.displayGeneratorSelection(generators);
        Generator generator = configureGenerator();

        output.displaySizeSelection();
        Size size = configureSize();

        Maze maze = generator.generate(size.height(), size.width());

        String render = renderer.render(maze);
        output.displayMaze(render);

        output.displaySolverSelection(solvers);
        Solver solver = configureSolver();

        output.displayStartCoordinateSelection();
        Coordinate start = configureCoordinate(maze);

        output.displayEndCoordinateSelection();
        Coordinate end = configureCoordinate(maze);

        String renderWithPath = renderer.render(maze, solver.solve(maze, start, end));
        output.displayMaze(renderWithPath);

    }

    private Coordinate configureCoordinate(Maze maze) {
        Coordinate coordinate = null;
        boolean isValidInput = false;
        do {
            try {
                coordinate = input.inputCoordinate(maze);
                isValidInput = true;
            } catch (Exception e) {
                output.displayException(e.getMessage());
            }
        } while (!isValidInput);
        return coordinate;
    }

    private Size configureSize() {
        Size size = null;
        boolean isValidInput = false;
        do {
            try {
                size = input.inputSize();
                isValidInput = true;
            } catch (Exception e) {
                output.displayException(e.getMessage());
            }
        } while (!isValidInput);
        return size;
    }

    private Generator configureGenerator() {
        Generator generator = null;
        boolean isValidInput = false;
        do {
            try {
                generator = input.inputGenerator(generators);
                isValidInput = true;
            } catch (Exception e) {
                output.displayException(e.getMessage());
            }
        } while (!isValidInput);
        return generator;
    }

    private Solver configureSolver() {
        Solver solver = null;
        boolean isValidInput = false;
        do {
            try {
                solver = input.inputSolver(solvers);
                isValidInput = true;
            } catch (Exception e) {
                output.displayException(e.getMessage());
            }
        } while (!isValidInput);
        return solver;
    }

}
