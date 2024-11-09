package ulpgc.es;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrixMultiplication {

    public static Map<Integer, Map<Integer, Double>> toSparse(double[][] matrix) {
        Map<Integer, Map<Integer, Double>> sparseMatrix = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    sparseMatrix.computeIfAbsent(i, k -> new HashMap<>()).put(j, matrix[i][j]);
                }
            }
        }
        return sparseMatrix;
    }

    public static double[][] multiplySparse(double[][] a, double[][] b) {
        int n = a.length;
        double[][] result = new double[n][n];

        Map<Integer, Map<Integer, Double>> sparseA = toSparse(a);
        Map<Integer, Map<Integer, Double>> sparseB = toSparse(b);

        for (int i : sparseA.keySet()) {
            for (int k : sparseA.get(i).keySet()) {
                if (sparseB.containsKey(k)) {
                    for (int j : sparseB.get(k).keySet()) {
                        result[i][j] += sparseA.get(i).get(k) * sparseB.get(k).get(j);
                    }
                }
            }
        }

        return result;
    }
}

