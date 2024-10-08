package backend.academy.mazes.maze;

public record Cell(Type type) {

    public enum Type { WALL, PASSAGE }

}
