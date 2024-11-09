package ulpgc.es;

public class StrassenMatrixMultiplication {

    public static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        if (n == 1) {
            return new double[][] {{a[0][0] * b[0][0]}};
        }

        int newSize = n / 2;
        double[][] a11 = new double[newSize][newSize];
        double[][] a12 = new double[newSize][newSize];
        double[][] a21 = new double[newSize][newSize];
        double[][] a22 = new double[newSize][newSize];
        double[][] b11 = new double[newSize][newSize];
        double[][] b12 = new double[newSize][newSize];
        double[][] b21 = new double[newSize][newSize];
        double[][] b22 = new double[newSize][newSize];

        // Splitting the matrices into sub-matrices
        splitMatrix(a, a11, a12, a21, a22);
        splitMatrix(b, b11, b12, b21, b22);

        // Applying Strassen's formulas for intermediate matrices
        double[][] m1 = multiply(add(a11, a22), add(b11, b22));
        double[][] m2 = multiply(add(a21, a22), b11);
        double[][] m3 = multiply(a11, subtract(b12, b22));
        double[][] m4 = multiply(a22, subtract(b21, b11));
        double[][] m5 = multiply(add(a11, a12), b22);
        double[][] m6 = multiply(subtract(a21, a11), add(b11, b12));
        double[][] m7 = multiply(subtract(a12, a22), add(b21, b22));

        // Calculating the result sub-matrices
        double[][] c11 = add(subtract(add(m1, m4), m5), m7);
        double[][] c12 = add(m3, m5);
        double[][] c21 = add(m2, m4);
        double[][] c22 = add(subtract(add(m1, m3), m2), m6);

        // Combining results into a single matrix
        double[][] result = new double[n][n];
        joinMatrix(c11, c12, c21, c22, result);
        return result;
    }

    // Helper method for adding two matrices
    private static double[][] add(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // Helper method for subtracting one matrix from another
    private static double[][] subtract(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    // Helper method to split a matrix into four submatrices
    private static void splitMatrix(double[][] source, double[][] a11, double[][] a12, double[][] a21, double[][] a22) {
        int newSize = source.length / 2;
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                a11[i][j] = source[i][j];                        // Top left
                a12[i][j] = source[i][j + newSize];              // Top right
                a21[i][j] = source[i + newSize][j];              // Bottom left
                a22[i][j] = source[i + newSize][j + newSize];    // Bottom right
            }
        }
    }

    // Helper method to join four submatrices into a single matrix
    private static void joinMatrix(double[][] c11, double[][] c12, double[][] c21, double[][] c22, double[][] result) {
        int newSize = c11.length;
        for (int i = 0; i < newSize; i++) {
            for (int j = 0; j < newSize; j++) {
                result[i][j] = c11[i][j];                        // Top left
                result[i][j + newSize] = c12[i][j];              // Top right
                result[i + newSize][j] = c21[i][j];              // Bottom left
                result[i + newSize][j + newSize] = c22[i][j];    // Bottom right
            }
        }
    }

}
