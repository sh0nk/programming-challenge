public class SubsequenceSh0nkDP implements SubsequenceI {
    /**
     * O(N^2)
     */
    @Override
    public int solve(int N, int K, int[] L) {
        int[][] d = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                d[i][j] = 0;
            }
        }

        int tmpMin = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int start = i;
            d[i][start] = L[start];
            System.out.println(String.format("%d %d= %d, tmpMin = %d", i, start, d[i][start], tmpMin));
            if (d[i][start] > K && tmpMin > start - i + 1) {
                tmpMin = start - i + 1;
            }
            for (int j = start + 1; j < N; j++) {
                d[i][j] = d[i][j - 1] + L[j];
                System.out.println(String.format("%d %d= %d, tmpMin = %d", i, j, d[i][j], tmpMin));
                if (d[i][j] > K && tmpMin > j - i + 1) {
                    tmpMin = j - i + 1;
                }
            }
        }

        if (tmpMin != K + 1) {
            return tmpMin;
        }
        return 0;
    }

}
