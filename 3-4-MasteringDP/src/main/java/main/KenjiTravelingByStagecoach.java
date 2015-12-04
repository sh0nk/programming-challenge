package main;

/**
 * Just Dijkstra's Algorithm, not the ansewer
 * 
 * @author kenji
 *
 */
public class KenjiTravelingByStagecoach implements TravelingByStagecoachInterface {

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

        int[] memo = new int[] { 99, 0, 99, 99 };

        int ret = loop(d, memo, a - 1, b - 1, 0, 99);

        System.out.println(ret);

        return 0;
    }

    int loop(int d[][], int memo[], int city, int end_city, int g_cost, int final_cost_min) {
        if (city == end_city)
            return g_cost;
        int[] path = d[city];
        for (int _city = 0; _city < path.length; _city++) {
            int local_cost = path[_city];
            if (local_cost == 0)
                continue;
            int _g_cost = local_cost + g_cost;
            int _g_min_cost = memo[_city];
            System.out.println("curr_city: " + (city + 1) + ", next_city: " + (_city + 1) + ", g_cost: " + _g_cost + ", g_min_cost: " + _g_min_cost);
            if (_g_min_cost > _g_cost) {
                memo[_city] = _g_cost;
                int ret = loop(d, memo, _city, end_city, _g_cost, final_cost_min);
                if (ret < final_cost_min)
                    final_cost_min = ret;
            } else {
                System.out.println(">> passed");
            }
        }
        return final_cost_min;
    }

}
