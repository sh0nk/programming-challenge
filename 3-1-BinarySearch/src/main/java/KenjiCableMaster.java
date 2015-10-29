import java.math.BigDecimal;

public class KenjiCableMaster implements CableMaster {

    @Override
    public String solve(int N, int K, double[] L) {

        double sum = 0.0;
        for (double l : L) {
            sum += l;
        }

        double ans = sum / K;

        boolean checked = false;
        boolean pre_checked;
        while (true) {
            pre_checked = checked;
            checked = check(L, K, ans);
            if (checked) {
                ans += 0.0001;
            } else {
                ans -= 0.0001;
            }
            if (pre_checked != checked) {
                break;
            }
        }
        System.out.println(String.valueOf(ans));
        BigDecimal bd = new BigDecimal(ans);
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_DOWN);

        return String.valueOf(bd2);
    }

    private boolean check(double[] L, int K, double ans) {
        int count = 0;
        for (double l : L) {
            count += l / ans;
        }
        return count >= K;
    }

}
