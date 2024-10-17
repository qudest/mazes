package backend.academy.mazes.output;

import backend.academy.mazes.maze.Cell;
import backend.academy.mazes.maze.coordinate.Coordinate;
import backend.academy.mazes.maze.Maze;
import java.util.List;
import java.util.stream.IntStream;

public class MazeRenderer implements Renderer {

    private static final String ANSI_RESET = "\u001B[0m";

    private static final String WALL_BACKGROUND = "\u001B[40m";
    private static final String PASSAGE_BACKGROUND = "\u001B[47m";
    private static final String PATH_BACKGROUND = "\u001B[42m";
    private static final String ENDS_BACKGROUND = "\u001B[46m";

    private static final String WALL_SPRITE = WALL_BACKGROUND + "   " + ANSI_RESET;
    private static final String PASSAGE_SPRITE = "   " + ANSI_RESET;
    private static final String BOOST_SPRITE = " \u001B[93m● " + ANSI_RESET;
    private static final String DELAY_SPRITE = " \u001B[91m╳ " + ANSI_RESET;

    private static final String LINE_BREAK = "\n";

    @Override
    public String render(Maze maze) {
        return render(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        StringBuilder stringBuilder = new StringBuilder();
        Cell[][] grid = maze.getGrid();

        renderColumnNumbers(stringBuilder, maze.getWidth());

        int lineCounter = 0;
        for (int row = 0; row < maze.getHeight(); row++) {
            stringBuilder.append(lineCounter);
            stringBuilder.append(" ");
            if (lineCounter < 10) {
                stringBuilder.append(" ");
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
        stringBuilder.append("   ");
        IntStream.range(0, width).forEach(i ->
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
