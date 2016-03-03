import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InclusionAndExclusionExample implements InclusionAndExclusionInterface {
    private static Logger LOGGER = LogManager.getLogger(InclusionAndExclusionExample.class);

    @Override
    public int solve(int n, int m, int[] a) {
        LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), n, m, a);
        return 0;
    }
}
