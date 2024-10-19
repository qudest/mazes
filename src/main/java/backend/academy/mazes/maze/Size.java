package backend.academy.mazes.maze;

public record Size(int height, int width) {

    public static final int MIN_SIZE = 3;
    public static final int MAX_SIZE = 100;

}
