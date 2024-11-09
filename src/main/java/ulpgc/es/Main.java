package ulpgc.es;

public class Main {

    public static void main(String[] args) {
        // Define sample matrices for testing
        int[][] matrixA = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        int[][] matrixB = {
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 14, 15},
                {16, 17, 18, 19}
        };

        // Basic matrix multiplication
        int[][] basicResult = BasicMatrixMultiplication.multiply(matrixA, matrixB);
        printMatrix("Basic Multiplication Result:", basicResult);

        // Optimized matrix multiplication
        int[][] optimizedResult = OptimizedMatrixMultiplication.multiply(matrixA, matrixB);
        printMatrix("Optimized Multiplication Result:", optimizedResult);

        // Strassen's matrix multiplication
        int[][] strassenResult = StrassenMatrixMultiplication.multiply(matrixA, matrixB);
        printMatrix("Strassen's Multiplication Result:", strassenResult);

        // Sparse matrix multiplication
        int[][] sparseResult = SparseMatrixMultiplication.multiplySparse(matrixA, matrixB);
        printMatrix("Sparse Multiplication Result:", sparseResult);
    }

    // Helper method to print matrices
    private static void printMatrix(String title, int[][] matrix) {
        System.out.println(title);
        for (int[] row : matrix) {
            for (double val : row) {
                System.out.printf("%6.2f ", val);
            }
            System.out.println();
        }
        System.out.println();
    }
}
