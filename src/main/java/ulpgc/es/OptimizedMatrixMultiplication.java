package ulpgc.es;

public class OptimizedMatrixMultiplication {

    public static double[][] multiply(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < n; k += 2) { // Loop unrolling
                    sum += a[i][k] * b[k][j];
                    if (k + 1 < n) {
                        sum += a[i][k + 1] * b[k + 1][j];
                    }
                }
                result[i][j] = sum;
            }
        }
        return result;
    }
}
