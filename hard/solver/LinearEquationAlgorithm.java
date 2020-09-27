package hard.solver;

/**
 * Interface for equation solver
 * @param <T> data to extract result
 */
@FunctionalInterface
public interface LinearEquationAlgorithm<T> {
    double[] solve(T t);
}
