package backend.academy;

import backend.academy.mazes.MazeManager;
import backend.academy.mazes.generator.HuntAndKill;
import backend.academy.mazes.generator.RecursiveBacktrackerGenerator;
import backend.academy.mazes.input.ConsoleInput;
import backend.academy.mazes.output.ConsoleOutput;
import backend.academy.mazes.output.MazeRenderer;
import backend.academy.mazes.solver.DFSSolver;
import backend.academy.mazes.solver.DijkstraSolver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        MazeManager mazeManager =
            new MazeManager(
                new ConsoleInput(new BufferedReader(new InputStreamReader(System.in, Charset.defaultCharset()))),
                new MazeRenderer(),
                new ConsoleOutput(System.out),
                List.of(new RecursiveBacktrackerGenerator(), new HuntAndKill()),
                List.of(new DFSSolver(), new DijkstraSolver())
            );
        mazeManager.start();
    }

}
