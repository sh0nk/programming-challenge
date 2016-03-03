import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;


public class InclusionAndExclusionNobby implements InclusionAndExclusionInterface {
    private static Logger LOGGER = LogManager.getLogger(InclusionAndExclusionNobby.class);

    @Override
    public int solve(int n, int m, int[] a) {
        LOGGER.info("{} is called with params, <{}> <{}> <{}>", this.getClass(), n, m, a);
        // 力技コードではダメなんす
        /*
        HashMap<Integer, Integer> resultMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int divisibleNum = n / a[i];
            for (int j = 1; j <= divisibleNum; j++) {
                resultMap.put(j*a[i], 1);
            }
        }
        return resultMap.size();
        */
        int res = 0;
        for (int i = 1; i < (1 << m); i++) {
            int num = 0;
            for (int j = i; j != 0; j >>= 1) {
                num += (j & 1);
            }
            long lcm = 1;
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    lcm = lcm / Gcd.gcd(lcm, a[j]) * a[j];
                    if (lcm > n) break;
                }
            }
            if (num % 2 == 0) {
                res -= (n / lcm);
            } else {
                res += (n / lcm);
            }
        }

        return res;
    }
}
