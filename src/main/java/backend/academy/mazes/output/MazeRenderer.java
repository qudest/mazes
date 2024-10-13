package backend.academy.mazes.output;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.List;
import java.util.stream.IntStream;

public class MazeRenderer implements Renderer {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    private static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    private static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    private static final String PASSAGE_SPRITE = ANSI_WHITE_BACKGROUND + "   " + ANSI_RESET;
    private static final String WALL_SPRITE = ANSI_BLACK_BACKGROUND + "   " + ANSI_RESET;
    private static final String PATH_SPRITE = ANSI_GREEN_BACKGROUND + "   " + ANSI_RESET;
    private static final String START_SPRITE = ANSI_CYAN_BACKGROUND + "   " + ANSI_RESET;
    private static final String END_SPRITE = START_SPRITE;
    private static final String LINE_BREAK = "\n";

    @Override
    public String render(Maze maze) {
        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();

        renderColumnNumbers(stringBuilder, grid);

        int lineCounter = 0;
        for (Cell[] row : grid) {

            stringBuilder.append(lineCounter);
            stringBuilder.append(" ");
            if (lineCounter < 10) {
                stringBuilder.append(" ");
            }

            for (Cell cell : row) {
                stringBuilder.append(renderCell(cell, null, null));
            }
            stringBuilder.append(LINE_BREAK);

            lineCounter++;
        }
        return stringBuilder.toString();
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();

        renderColumnNumbers(stringBuilder, grid);

        int lineCounter = 0;
        for (int row = 0; row < grid.length; row++) {

            stringBuilder.append(lineCounter);
            stringBuilder.append(" ");
            if (lineCounter < 10) {
                stringBuilder.append(" ");
            }

            for (int col = 0; col < grid[row].length; col++) {
                Cell cell = grid[row][col];
                Coordinate coordinate = new Coordinate(row, col);
                stringBuilder.append(renderCell(cell, coordinate, path));
            }
            stringBuilder.append(LINE_BREAK);

            lineCounter++;
        }
        return stringBuilder.toString();
    }

    private void renderColumnNumbers(StringBuilder stringBuilder, Cell[][] grid) {
        stringBuilder.append("   ");
        IntStream.range(0, grid[0].length).forEach(i ->
        {
            if (i < 10) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(i);
            stringBuilder.append(" ");
        });
        stringBuilder.append(LINE_BREAK);
    }

    private String renderCell(Cell cell, Coordinate cellCoordinate, List<Coordinate> path) {
        if (cell.type().equals(Cell.Type.WALL)) {
            return WALL_SPRITE;
        } else {
            return renderPassage(cellCoordinate, path);
        }
    }

    private String renderPassage(Coordinate cellCoordinate, List<Coordinate> path) {
        if (path != null && !path.isEmpty()) {
            if (path.getFirst().equals(cellCoordinate)) {
                return START_SPRITE;
            } else if (path.getLast().equals(cellCoordinate)) {
                return END_SPRITE;
            } else if (path.contains(cellCoordinate)) {
                return PATH_SPRITE;
            }
        }
        return PASSAGE_SPRITE;
    }

}
