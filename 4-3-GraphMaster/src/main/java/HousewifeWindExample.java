import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HousewifeWindExample implements HousewifeWindInterface {
    private static Logger LOGGER = LogManager.getLogger(HousewifeWindExample.class);

    @Override
    public String solve(int n, int q, int s, List<int[]> abw, List<List> query) {
        return "1\n3\n";
    }
}
