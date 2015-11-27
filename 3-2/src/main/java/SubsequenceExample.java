public class SubsequenceExample implements SubsequenceI {

    @Override
    public int solve(int n, int S, int[] a) {

        int res = n + 1;
        int s = 0, t = 0, sum = 0;

        for (;;) {
            while (t < n && sum < S) {
                sum += a[t++];
            }
            if (sum < S)
                break;
            res = Math.min(res, t - s);
            sum -= a[s++];
        }

        if (res > n) {
            res = 0;
        }

        return res;
    }

}
