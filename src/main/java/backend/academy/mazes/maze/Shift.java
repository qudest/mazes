package backend.academy.mazes.maze;

public record Shift(int y, int x) {

    public Coordinate getShiftedCoordinate(Coordinate coordinate) {
        return new Coordinate(coordinate.y() + y, coordinate.x() + x);
    }

}
