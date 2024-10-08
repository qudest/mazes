package backend.academy.mazes.output;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.List;

public class MazeRenderer implements Renderer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String PASSAGE_SPRITE = ANSI_WHITE_BACKGROUND + "   " + ANSI_RESET;
    private static final String WALL_SPRITE = ANSI_BLACK_BACKGROUND + "   " + ANSI_RESET;
    private static final String PATH_SPRITE = ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET;
    private static final String LINE_BREAK = "\n";

    @Override
    public String render(Maze maze) {
        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (Cell[] row : grid) {
            for (Cell cell : row) {
                if (cell.type().equals(Cell.Type.WALL)) {
                    stringBuilder.append(WALL_SPRITE);
                }
                if (cell.type().equals(Cell.Type.PASSAGE)) {
                    stringBuilder.append(PASSAGE_SPRITE);
                }
            }
            stringBuilder.append(LINE_BREAK);
        }
        return stringBuilder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                Cell cell = grid[row][col];
                Coordinate coordinate = new Coordinate(row, col);
                if (cell.type().equals(Cell.Type.WALL)) {
                    stringBuilder.append(WALL_SPRITE);
                } else if (cell.type().equals(Cell.Type.PASSAGE)) {
                    if (path.contains(coordinate)) {
                        stringBuilder.append(PATH_SPRITE);
                    } else {
                        stringBuilder.append(PASSAGE_SPRITE);
                    }
                }
            }
            stringBuilder.append(LINE_BREAK);
        }
        return stringBuilder.toString();
    }

}
