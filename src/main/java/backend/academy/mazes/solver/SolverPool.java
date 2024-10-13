package backend.academy.mazes.solver;

import java.util.List;

public record SolverPool(List<Solver> solvers) {

    @Override public List<Solver> solvers() {
        return List.copyOf(solvers);
    }

    public Solver getSolver(int index) {
        return solvers.get(index);
    }

}
