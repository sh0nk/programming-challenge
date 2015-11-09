
public class NoripiSubsequence2 implements SubsequenceI {

  @Override
  public int solve(int N, int K, int[] L) {
    int currentSum = 0;
    int startIndex = 0;

    int length = N;

    for (int i = 0; i < N; i++) {
      currentSum += L[i];

      // decrease value as much as possible
      while (currentSum >= K) {
        currentSum -= L[startIndex];
        startIndex++;

        if (currentSum >= K) {
          length = Math.min(length, i - startIndex + 1);
        }
      }
    }

    return length;
  }

}
