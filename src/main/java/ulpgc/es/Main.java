package ulpgc.es;

public class Main {

    public static void main(String[] args) {
        // Define sample matrices for testing
        double[][] matrixA = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        double[][] matrixB = {
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };

        // Basic matrix multiplication
        double[][] basicResult = BasicMatrixMultiplication.multiply(matrixA, matrixB);
        printMatrix("Basic Multiplication Result:", basicResult);

        // Optimized matrix multiplication
        double[][] optimizedResult = OptimizedMatrixMultiplication.multiply(matrixA, matrixB);
        printMatrix("Optimized Multiplication Result:", optimizedResult);

        // Strassen's matrix multiplication
        double[][] strassenResult = StrassenMatrixMultiplication.multiply(matrixA, matrixB);
        printMatrix("Strassen's Multiplication Result:", strassenResult);

        // Sparse matrix multiplication
        double[][] sparseResult = SparseMatrixMultiplication.multiplySparse(matrixA, matrixB);
        printMatrix("Sparse Multiplication Result:", sparseResult);
    }

    // Helper method to print matrices
    private static void printMatrix(String title, double[][] matrix) {
        System.out.println(title);
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%6.2f ", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}
