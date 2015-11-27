import java.util.Arrays;

public class NoripiFourValuesWhoseSumIsZero implements FourValuesWhoseSumIsZeroI {

  @Override
  public int solve(int n, int[] A, int[] B, int[] C, int[] D) {
    int[] AxB = new int[n * n];
    int[] CxD = new int[n * n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        AxB[n * i + j] = A[i] + B[j];
        CxD[n * i + j] = C[i] + D[j];
      }
    }

    Arrays.sort(AxB);
    Arrays.sort(CxD);

    int count = 0;
    int i = 0;
    int j = 0;
    while (i < n * n && j < n * n) {
      int axb = AxB[i];
      int cxd = CxD[n * n - j - 1];

      if (axb + cxd == 0) {
        count++;
        i++;
        j++;
        continue;
      }

      if (axb + cxd < 0) {
        // increase axb
        i++;
      } else {
        // decrease cxd
        j++;
      }
    }

    return count;
  }

}
