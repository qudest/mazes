package backend.academy.mazes.maze;

import backend.academy.mazes.utils.SecureRandomSingleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Getter;

public record Cell(Type type) {

    private static final double DEFAULT_PASSAGE_CHANCE = 0.8;

    @Getter public enum Type {
        WALL(100),
        PASSAGE(2),
        BOOST(1),
        DELAY(3);

        private final int weight;

        Type(int weight) {
            this.weight = weight;
        }
    }

    public int getWeight() {
        return type.weight();
    }

    public static Type getRandomPassage() {
        List<Type> surfaces = new ArrayList<>(
            Arrays.stream(Type.values())
                .filter(t -> t != Type.WALL && t != Type.PASSAGE)
                .toList());

        double i = SecureRandomSingleton.getInstance().nextDouble();
        if (i > DEFAULT_PASSAGE_CHANCE) {
            Collections.shuffle(surfaces);
            return surfaces.getFirst();
        } else {
            return Type.PASSAGE;
        }
    }

}
