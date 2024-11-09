import org.openjdk.jmh.annotations.*;
import ulpgc.es.BasicMatrixMultiplication;
import ulpgc.es.OptimizedMatrixMultiplication;
import ulpgc.es.SparseMatrixMultiplication;
import ulpgc.es.StrassenMatrixMultiplication;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
public class MatrixMultiplicationBenchmark {

    private double[][] matrixA;
    private double[][] matrixB;

    @Setup
    public void setup() {
        // Initialize matrices A and B with example data
        matrixA = new double[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        matrixB = new double[][]{
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };
    }

    @Benchmark
    public double[][] basicMultiplication() {
        return BasicMatrixMultiplication.multiply(matrixA, matrixB);
    }

    @Benchmark
    public double[][] optimizedMultiplication() {
        return OptimizedMatrixMultiplication.multiply(matrixA, matrixB);
    }

    @Benchmark
    public double[][] strassenMultiplication() {
        return StrassenMatrixMultiplication.multiply(matrixA, matrixB);
    }

    @Benchmark
    public double[][] sparseMultiplication() {
        return SparseMatrixMultiplication.multiplySparse(matrixA, matrixB);
    }
}
