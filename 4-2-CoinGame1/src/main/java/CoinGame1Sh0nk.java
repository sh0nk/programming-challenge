import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoinGame1Sh0nk implements CoinGame1Interface {
  private static Logger LOGGER = LogManager.getLogger(CoinGame1Sh0nk.class);

  @Override
  public String solve(int x, int k, int[] a) {
    LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), x, k, a);

      int dp[][] = new int[100][x];

      for (int i = 0; i < x; i++) {
          dp[0][i] = 0;
      }
      for (int el: a) {
          dp[0][x - 1 - el] = 1;
      }
      LOGGER.info("{} (init)", dp[0]);

      for (int i = 1; i < 100; i++) {
          int who = i % 2 + 1; // 1:bob 2:alice
          for (int el: a) {
              for (int j = x - 1; j >= 0; j--) {
                  if (dp[i - 1][j] == (who % 2 == 0 ? 1: 2) && j - el >= 0) {
                      if (j - el == 0) {
                          dp[i][j - el] = who;
                          LOGGER.info("{}", dp[i]);
                          return (who % 2 == 0 ? "Alice": "Bob");
                      }
                      dp[i][j - el] = who;
                  }
              }
          }
          LOGGER.info("{}", dp[i]);
      }

    return "Alice";
  }
}
