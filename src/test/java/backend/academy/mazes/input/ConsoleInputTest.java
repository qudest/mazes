package backend.academy.mazes.input;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.Size;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.solver.Solver;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ConsoleInputTest {

    @Test
    void inputSize() throws IOException {
        try (BufferedReader reader = Mockito.mock(BufferedReader.class)) {
            Input consoleInput = new ConsoleInput(reader);
            when(reader.readLine()).thenReturn("10 10");
            Size size = consoleInput.inputSize();
            assertEquals(new Size(10, 10), size);
            when(reader.readLine()).thenReturn("2 2"); // height, width >= 3
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("101 101"); // height, width <= 100
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("5 5 5");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("5");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("abc abc");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("");
            assertThrows(IOException.class, consoleInput::inputSize);
        }
    }

    @Test
    void inputCoordinate() throws IOException {
        Maze maze = new Maze(
            new Cell[][] {
                { new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL) },
                { new Cell(Cell.Type.WALL), new Cell(Cell.Type.PASSAGE), new Cell(Cell.Type.WALL) },
                { new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL), new Cell(Cell.Type.WALL) },

            }
        );

        try (BufferedReader reader = Mockito.mock(BufferedReader.class)) {
            ConsoleInput consoleInput = new ConsoleInput(reader);
            when(reader.readLine()).thenReturn("1 1");
            Coordinate coordinate = consoleInput.inputCoordinate(maze);
            assertEquals(new Coordinate(1, 1), coordinate);
            when(reader.readLine()).thenReturn("-1 -1");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("5 5 5");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("5");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("abc abc");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("");
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("0, 0"); // стена
            assertThrows(IOException.class, consoleInput::inputSize);
            when(reader.readLine()).thenReturn("5 5"); // выходит за размеры лабиринта
            assertThrows(IOException.class, consoleInput::inputSize);

        }

    }

    @Test
    void inputGenerator() throws IOException {
        Generator generator = new Generator() {
            @Override
            public Maze generate(int height, int width) {
                return null;
            }

            @Override
            public Maze generate(int height, int width, Coordinate start) {
                return null;
            }
        };
        List<Generator> generators = List.of(generator);
        try (BufferedReader reader = Mockito.mock(BufferedReader.class)) {
            ConsoleInput consoleInput = new ConsoleInput(reader);
            when(reader.readLine()).thenReturn("1");
            Generator inputtedGenerator = consoleInput.inputGenerator(generators);
            assertEquals(generator, inputtedGenerator);
            when(reader.readLine()).thenReturn("2");
            assertThrows(IOException.class, () -> consoleInput.inputGenerator(generators));

        }
    }

    @Test
    void inputSolver() throws IOException {
        Solver solver = (_, _, _) -> List.of();
        List<Solver> solvers = List.of(solver);
        try (BufferedReader reader = Mockito.mock(BufferedReader.class)) {
            ConsoleInput consoleInput = new ConsoleInput(reader);
            when(reader.readLine()).thenReturn("1");
            Solver inputtedSolver = consoleInput.inputSolver(solvers);
            assertEquals(solver, inputtedSolver);
            when(reader.readLine()).thenReturn("2");
            assertThrows(IOException.class, () -> consoleInput.inputSolver(solvers));

        }
    }
}
