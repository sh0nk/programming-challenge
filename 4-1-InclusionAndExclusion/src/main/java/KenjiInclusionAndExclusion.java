import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KenjiInclusionAndExclusion implements InclusionAndExclusionInterface {
  private static Logger LOGGER = LogManager.getLogger(KenjiInclusionAndExclusion.class);

  @Override
  public int solve(int n, int m, int[] a) {

    int global_cnt = 0;

    // Arrays.sort(a);

    for (int value = 1; value <= n; value++) {
      for (int aa = 0; aa < m; aa++) {
        if (value % a[aa] == 0) {
          global_cnt++;
          break;
        }
      }
    }

    System.out.println(global_cnt);
    return global_cnt;
  }
}
