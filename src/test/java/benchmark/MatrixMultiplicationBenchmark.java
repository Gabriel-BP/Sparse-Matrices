package benchmark;

import ulpgc.es.BasicMatrixMultiplication;
import ulpgc.es.OptimizedMatrixMultiplication;
import ulpgc.es.SparseMatrixMultiplication;
import ulpgc.es.StrassenMatrixMultiplication;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class MatrixMultiplicationBenchmark {

    public static void main(String[] args) {
        // Test with different matrix sizes
        int[] matrixSizes = {128, 256, 512, 1024, 2048};  // Matrix sizes to test
        double[] sparsityLevels = {0.3, 0.5, 0.7, 0.9};  // Different sparsity levels to test

        // Run the tests
        for (int size : matrixSizes) {
            System.out.println("Testing matrix size: " + size + "x" + size);

            // Generate random dense matrices
            int[][] matrixA = generateRandomMatrix(size, 0.0);  // Dense matrix
            int[][] matrixB = generateRandomMatrix(size, 0.0);  // Dense matrix

            // Test basic matrix multiplication
            testMatrixMultiplication("Basic", matrixA, matrixB);

            // Test optimized matrix multiplication
            testMatrixMultiplication("Optimized", matrixA, matrixB);

            // Test Strassen matrix multiplication
            testMatrixMultiplication("Strassen", matrixA, matrixB);

            //Test sparse matrix multiplication with different sparsity levels
             for (double sparsityLevel : sparsityLevels) {
                System.out.println("Testing Sparse Multiplication with " + (int)(sparsityLevel * 100) + "% non-zero elements:");
                matrixA = generateRandomMatrix(size, sparsityLevel);
                matrixB = generateRandomMatrix(size, sparsityLevel);
                testMatrixMultiplication("Sparse", matrixA, matrixB);
            }

            System.out.println();  // Separator for each matrix size
        }
    }

    // Method to generate random matrices with specified sparsity level
    public static int[][] generateRandomMatrix(int size, double sparsityLevel) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.random() >= sparsityLevel) {
                    matrix[i][j] = (int) (Math.random() * 10);  // Random integer values
                }
            }
        }
        return matrix;
    }

    // Method to test different matrix multiplication methods
    public static void testMatrixMultiplication(String method, int[][] matrixA, int[][] matrixB) {
        long startTime = System.nanoTime();
        long beforeMemory = getMemoryUsage();

        // Perform the matrix multiplication based on the method
        int[][] result = null;
        switch (method) {
            case "Basic":
                result = BasicMatrixMultiplication.multiply(matrixA, matrixB);
                break;
            case "Optimized":
                result = OptimizedMatrixMultiplication.multiply(matrixA, matrixB);
                break;
            case "Strassen":
                result = StrassenMatrixMultiplication.multiply(matrixA, matrixB);
                break;
            case "Sparse":
                result = SparseMatrixMultiplication.multiplySparse(matrixA, matrixB);
                break;
            default:
                System.out.println("Unknown multiplication method: " + method);
                return;
        }

        long endTime = System.nanoTime();
        long afterMemory = getMemoryUsage();
        long elapsedTime = endTime - startTime;
        long memoryUsed = afterMemory - beforeMemory;

        // Output results
        System.out.println(method + " multiplication took: " + elapsedTime / 1_000_000 + " ms");
        System.out.println(method + " memory used: " + memoryUsed + " bytes");

        // Optionally print result matrix
        // printMatrix(result); // Uncomment to print result matrix (may be large)
    }

    // Helper method to get accurate memory usage
    public static long getMemoryUsage() {
        System.gc();
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        return heapMemoryUsage.getUsed();
    }
}
