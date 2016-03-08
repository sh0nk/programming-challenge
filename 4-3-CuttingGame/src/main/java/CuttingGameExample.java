import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CuttingGameExample implements CuttingGameInterface {
  private static Logger LOGGER = LogManager.getLogger(CuttingGameExample.class);

  @Override
  public String solve(int x, int k) {
    LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), x, k);
    return "LOSE";
  }
}
