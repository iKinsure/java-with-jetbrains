package hard.solver;

@FunctionalInterface
public interface LinearEquationAlgorithm<T> {
    double[] solve(T t);
}
