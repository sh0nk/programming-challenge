package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kenji_T_TravelingByStagecoach implements TravelingByStagecoachInterface {

    /**
     * <pre>
     * n=2 乗車券
     * m=4 都市数
     * a=2 start
     * b=1 end
     * t = {3, 1} 頭数
     * </pre>
     */
    @Override
    public double solve(int n, int m, int a, int b, int[] t, int[][] d) {

        // initialize cost memo
        double[][] memo = new double[m][t.length];
        for (int city = 0; city < m; city++) {
            for (int ticket = 0; ticket < n; ticket++) {
                if (city == a - 1) {
                    memo[city][ticket] = 0.0;
                } else {
                    memo[city][ticket] = Double.MAX_VALUE;
                }
            }
        }

        int[] orig_t = Arrays.copyOf(t, t.length);
        double ret = loop(0, d, memo, -1, a - 1, b - 1, 0.0, Double.MAX_VALUE, t, orig_t);

        System.out.println("==================");
        System.out.println(ret);
        System.out.println("==================");

        return ret;
    }

    double loop(int cnt, int d[][], double memo[][], int prev_city, int city, int end_city, double g_cost, double final_cost_min, int[] t, int[] orig_t) {
        if (city == end_city) {
            return g_cost;
        }

        int[] next_cities = d[city];
        for (int next_city = 0; next_city < next_cities.length; next_city++) {
            double local_cost = next_cities[next_city];
            if (local_cost == 0 || prev_city == next_city)
                continue;
            for (int it = 0; it < t.length; it++) {
                int t2[] = new int[t.length-1];
                int a=0;
                for(int i=0;i<t.length; i++) {
                    if (i==it)
                        continue;
                    t2[a] = t[i];
                    a++;
                }
                double _g_cost = local_cost / t[it] + g_cost;
                double _g_min_cost = memo[next_city][it];

                System.out.println(cnt + " [ " + (city + 1) + " -> " + (next_city + 1) + " ]" + ", t" + it + "(" + t[it] +
                        ")" + ", gcost: " + _g_cost + ", gcost_min: " + _g_min_cost + "   " + t2.length);
                //if (_g_cost < _g_min_cost) {
                    memo[next_city][it] = _g_cost;
                    double ret = loop(cnt+1, d, memo, city, next_city, end_city, _g_cost, final_cost_min, t2, orig_t);
                    if (ret < final_cost_min) {
                        System.out.println("*** " + ret + " < " + final_cost_min + " [ " + (city + 1) + " -> " + (next_city + 1) + " ]" +
                                ", t" + it + "(" + t[it] + ")" +
                                ", gcost: " + _g_cost + ", gcost_min: " + _g_min_cost);
                        final_cost_min = ret;
                    }
                //}
            }
        }
        return final_cost_min;
    }

}
