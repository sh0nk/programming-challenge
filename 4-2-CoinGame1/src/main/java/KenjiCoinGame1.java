import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KenjiCoinGame1 implements CoinGame1Interface {
  private static Logger LOGGER = LogManager.getLogger(KenjiCoinGame1.class);

  @Override
  public String solve(int x, int k, int[] a) {
    LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), x, k, a);

    List<Integer> array = new ArrayList<>();
    for (int i : a) {
      array.add(i);
    }
    Collections.reverse(array);

    return loop(x, array);

  }

  String loop(int x, List<Integer> array) {
    if (array.size() == 0) {
      return "Bob";
    }

    for (int i : array) {
      int win_n = x % i;
      if (win_n != 0) {
        if (array.contains(win_n)) {
          return "Alice";
        }
      }
    }
    array.remove(0);
    loop(x, array);

    return "Bob";
  }

}
