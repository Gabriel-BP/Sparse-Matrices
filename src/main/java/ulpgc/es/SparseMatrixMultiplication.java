package ulpgc.es;

import java.util.HashMap;
import java.util.Map;

public class SparseMatrixMultiplication {

    public static Map<Integer, Map<Integer, Integer>> toSparse(int[][] matrix) {
        Map<Integer, Map<Integer, Integer>> sparseMatrix = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    sparseMatrix.computeIfAbsent(i, k -> new HashMap<>()).put(j, matrix[i][j]);
                }
            }
        }
        return sparseMatrix;
    }

    public static int[][] multiplySparse(int[][] a, int[][] b) {
        int n = a.length;
        int[][] result = new int[n][n];

        Map<Integer, Map<Integer, Integer>> sparseA = toSparse(a);
        Map<Integer, Map<Integer, Integer>> sparseB = toSparse(b);

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
