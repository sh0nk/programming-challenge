import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoinGame1Nobby implements CoinGame1Interface {
  private static Logger LOGGER = LogManager.getLogger(CoinGame1Nobby.class);
  private boolean[] win;
  private int X;
  private int K;
  private int[] A;

  @Override
  public String solve(int x, int k, int[] a) {
    LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), x, k, a);
    win = new boolean[x + 1];
    X = x;
    K = k;
    A = a;
    win[0] = false;
    for (int j = 1; j <= X; j++) {
      win[j] = false;
      for (int i = 0; i < K; i++) {
        if (!win[j] ) {
          win[j] = (A[i] <= j && !win[j -A[i]]);
        }
        //win[j] |= A[i] <= j && !win[j -A[i]];
      }
    }

    if (win[X]) {
      return "Alice";
    }
    return "Bob";
  }
}
