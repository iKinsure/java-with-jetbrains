package org.ikinsure.solver;

/**
 * Interface for equation solver
 * @param <T> data to extract result
 */
@FunctionalInterface
public interface LinearEquationAlgorithm<T, R> {
    R[] solve(T t);
}
