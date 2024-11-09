package ulpgc.es;

public class StrassenMatrixMultiplication {

    public static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;
        if (n == 1) {
            return new int[][] {{a[0][0] * b[0][0]}};
        }

        int newSize = n / 2;
        int[][] a11 = new int[newSize][newSize];
        int[][] a12 = new int[newSize][newSize];
        int[][] a21 = new int[newSize][newSize];
        int[][] a22 = new int[newSize][newSize];
        int[][] b11 = new int[newSize][newSize];
        int[][] b12 = new int[newSize][newSize];
        int[][] b21 = new int[newSize][newSize];
        int[][] b22 = new int[newSize][newSize];

        // Splitting the matrices into sub-matrices
        splitMatrix(a, a11, a12, a21, a22);
        splitMatrix(b, b11, b12, b21, b22);

        // Applying Strassen's formulas for intermediate matrices
        int[][] m1 = multiply(add(a11, a22), add(b11, b22));
        int[][] m2 = multiply(add(a21, a22), b11);
        int[][] m3 = multiply(a11, subtract(b12, b22));
        int[][] m4 = multiply(a22, subtract(b21, b11));
        int[][] m5 = multiply(add(a11, a12), b22);
        int[][] m6 = multiply(subtract(a21, a11), add(b11, b12));
        int[][] m7 = multiply(subtract(a12, a22), add(b21, b22));

        // Calculating the result sub-matrices
        int[][] c11 = add(subtract(add(m1, m4), m5), m7);
        int[][] c12 = add(m3, m5);
        int[][] c21 = add(m2, m4);
        int[][] c22 = add(subtract(add(m1, m3), m2), m6);

        // Combining results into a single matrix
        int[][] result = new int[n][n];
        joinMatrix(c11, c12, c21, c22, result);
        return result;
    }

    // Helper method for adding two matrices
    private static int[][] add(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // Helper method for subtracting one matrix from another
    private static int[][] subtract(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    // Helper method to split a matrix into four submatrices
    private static void splitMatrix(int[][] source, int[][] a11, int[][] a12, int[][] a21, int[][] a22) {
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
    private static void joinMatrix(int[][] c11, int[][] c12, int[][] c21, int[][] c22, int[][] result) {
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
