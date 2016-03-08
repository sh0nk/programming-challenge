import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CoinGame1Example implements CoinGame1Interface {
  private static Logger LOGGER = LogManager.getLogger(CoinGame1Example.class);

  @Override
  public String solve(int x, int k, int[] a) {
    LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), x, k, a);
    return "Alice";
  }
}
