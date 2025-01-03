package backend.academy.mazes.output;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.Maze;
import backend.academy.mazes.maze.coordinate.Coordinate;
import java.util.List;
import java.util.stream.IntStream;

public class MazeRenderer implements Renderer {

    private static final String ANSI_RESET = "\u001B[0m";

    private static final String WALL_BACKGROUND = "\u001B[40m";
    private static final String PASSAGE_BACKGROUND = "\u001B[47m";
    private static final String PATH_BACKGROUND = "\u001B[42m";
    private static final String ENDS_BACKGROUND = "\u001B[46m";

    private static final String EMPTY_CELL_SPRITE = "   ";
    private static final String WALL_SPRITE = WALL_BACKGROUND + EMPTY_CELL_SPRITE + ANSI_RESET;
    private static final String PASSAGE_SPRITE = EMPTY_CELL_SPRITE + ANSI_RESET;
    private static final String BOOST_SPRITE = " \u001B[93m● " + ANSI_RESET;
    private static final String DELAY_SPRITE = " \u001B[91m╳ " + ANSI_RESET;

    private static final Character LINE_BREAK = '\n';
    private static final Character SEPARATOR = ' ';

    @Override
    public String render(Maze maze) {
        return render(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        if (maze == null || maze.isEmpty()) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();

        renderColumnNumbers(stringBuilder, maze.getWidth());

        int lineCounter = 0;
        for (int row = 0; row < maze.getHeight(); row++) {
            stringBuilder.append(lineCounter);
            stringBuilder.append(SEPARATOR);
            if (String.valueOf(lineCounter).length() == 1) {
                stringBuilder.append(SEPARATOR);
            }

            for (int col = 0; col < maze.getWidth(); col++) {
                Cell cell = grid[row][col];
                Coordinate coordinate = new Coordinate(row, col);
                stringBuilder.append(renderCell(cell, coordinate, path));
            }
            stringBuilder.append(LINE_BREAK);

            lineCounter++;
        }
        return stringBuilder.toString();
    }

    private void renderColumnNumbers(StringBuilder stringBuilder, int width) {
        stringBuilder.append(EMPTY_CELL_SPRITE);
        IntStream.range(0, width).forEach(i -> {
            if (String.valueOf(i).length() == 1) {
                stringBuilder.append(SEPARATOR);
            }
            stringBuilder.append(i);
            stringBuilder.append(SEPARATOR);
        });
        stringBuilder.append(LINE_BREAK);
    }

    private String renderCell(Cell cell, Coordinate cellCoordinate, List<Coordinate> path) {
        if (cell.type() == Cell.Type.WALL) {
            return WALL_SPRITE;
        } else {
            return renderPassage(cell, cellCoordinate, path);
        }
    }

    private String renderPassage(Cell cell, Coordinate cellCoordinate, List<Coordinate> path) {
        return getBackgroundColor(cellCoordinate, path) + getSprite(cell);
    }

    private String getBackgroundColor(Coordinate cellCoordinate, List<Coordinate> path) {
        if (path != null && !path.isEmpty() && path.contains(cellCoordinate)) {
            return path.getFirst().equals(cellCoordinate) || path.getLast().equals(cellCoordinate)
                ? ENDS_BACKGROUND
                : PATH_BACKGROUND;
        }
        return PASSAGE_BACKGROUND;
    }

    private String getSprite(Cell cell) {
        return switch (cell.type()) {
            case BOOST -> BOOST_SPRITE;
            case DELAY -> DELAY_SPRITE;
            default -> PASSAGE_SPRITE;
        };
    }

}
