package backend.academy.mazes.output;

import backend.academy.mazes.maze.Maze;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MazeRendererTest {

    private final Renderer renderer = new MazeRenderer();

    @Test
    void renderEmptyMaze() {
        assertEquals("", renderer.render(null));
        Maze maze2 = new Maze(null);
        assertEquals("", renderer.render(maze2));
    }
}
