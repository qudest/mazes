package backend.academy.mazes.output;

import backend.academy.mazes.generator.Generator;
import backend.academy.mazes.solver.Solver;
import java.io.PrintStream;
import java.util.List;

public class ConsoleOutput implements Output {

    private final PrintStream printStream;

    public ConsoleOutput(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void displayStartScreen() {
        printStream.println("""

            -----------------------------------------------
            ███╗   ███╗  █████╗  ███████╗ ███████╗ ███████╗
            ████╗ ████║ ██╔══██╗ ╚══███╔╝ ██╔════╝ ██╔════╝
            ██╔████╔██║ ███████║   ███╔╝  █████╗   ███████╗
            ██║╚██╔╝██║ ██╔══██║  ███╔╝   ██╔══╝   ╚════██║
            ██║ ╚═╝ ██║ ██║  ██║ ███████╗ ███████╗ ███████║
            ╚═╝     ╚═╝ ╚═╝  ╚═╝ ╚══════╝ ╚══════╝ ╚══════╝
            -----------------------------------------------

            Добро пожаловать в Mazes

            -----------------------------------------------
            """);
    }

    @Override
    public void displaySizeSelection() {
        printStream.println("""

            Выберите размеры лабиринта в формате {высота ширина}
            Минимальный размер 3x3
            Максимальный размер 100x100
            """);
    }

    @Override
    public void displayGeneratorSelection(List<Generator> generators) {
        printStream.println("Выберите алгоритм генерации лабиринта: ");
        int counter = 1;
        for (Generator generator : generators) {
            printStream.println(counter + ". " + generator);
            counter++;
        }
        printStream.println();

    }

    @Override
    public void displaySolverSelection(List<Solver> solvers) {
        printStream.println("Выберите алгоритм поиска пути: ");
        int counter = 1;
        for (Solver solver : solvers) {
            printStream.println(counter + ". " + solver);
            counter++;
        }
        printStream.println();
    }

    @Override
    public void displayException(String exceptionMessage) {
        printStream.println("Ошибка: " + exceptionMessage);
    }

    @Override
    public void displayMaze(String maze) {
        printStream.println("\nЛабиринт:");
        printStream.println(maze);
    }

    @Override
    public void displayStartCoordinateSelection() {
        printStream.println("\nВвод стартовой координаты");
        displayCoordinateSelection();
    }

    @Override
    public void displayEndCoordinateSelection() {
        printStream.println("\nВвод конечной координаты");
        displayCoordinateSelection();
    }

    private void displayCoordinateSelection() {
        printStream.println("Введите координаты в формате {y x}:");
    }

}
