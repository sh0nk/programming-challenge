import java.util.Arrays;

public class NoripiCableMaster implements CableMaster {

  @Override
  public String solve(int N, int K, double[] L) {
    double estimatedPartLength = Arrays.stream(L).sum() / K;

    int count = K;
    double pivot = estimatedPartLength / 2;

    int actualCount = 0;
    double answer = 0;
    while (pivot > 0.0005) {
      final double partLen = estimatedPartLength;
      actualCount = (int) Arrays.stream(L).map(l -> (int) Math.floor(l / partLen)).sum();

      // exact number
      if (actualCount == count) {
        answer = Math.max(estimatedPartLength, answer);
        estimatedPartLength += pivot;
      }

      // too long
      if (actualCount < count) {
        estimatedPartLength -= pivot;
      }

      // too short
      if (actualCount > count) {
        estimatedPartLength += pivot;
      }

      pivot = pivot / 2;
    }

    int answerInt = (int) Math.floor(answer *= 100);
    String answerStr = String.format("%d", answerInt);

    return answerStr.substring(0, answerStr.length() - 2) + "."
        + answerStr.substring(answerStr.length() - 2);
  }
}
