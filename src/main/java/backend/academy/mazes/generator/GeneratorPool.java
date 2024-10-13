package backend.academy.mazes.generator;

import java.util.List;

public record GeneratorPool(List<Generator> generators) {

    @Override public List<Generator> generators() {
        return List.copyOf(generators);
    }

    public Generator getGenerator(int index) {
        return generators.get(index);
    }

}
