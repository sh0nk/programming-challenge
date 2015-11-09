public class NoripiSubsequence implements SubsequenceI {

  @Override
  public int solve(int N, int K, int[] L) {
    // a(i, j) = sum(k=i to i + j)
    int[][] sumList = new int[N][N];

    int minLength = N;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N - j; j++) {
        switch (j) {
          case 0:
            // a(i, 0) = 0
            sumList[i][j] = 0;
            break;
          case 1:
            // a(i, 1) = L[i];
            sumList[i][j] = L[i];
            break;
          default:
            // a(i, j) = a(i, j-1) + L[i+j]
            sumList[i][j] = sumList[i][j - 1] + L[i + j];
        }

        if (sumList[i][j] >= K) {
          minLength = Math.min(minLength, j);
          break;
        }
      }
    }

    return minLength;
  }

}
