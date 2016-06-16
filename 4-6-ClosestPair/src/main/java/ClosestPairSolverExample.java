import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ClosestPairSolverExample implements IClosestPairSolver {
    private static Logger logger = LogManager.getLogger(ClosestPairSolverExample.class);

    //TODO sorry , this is not correct.
    public double solve(int n, double[] X, double[] Y) {
        Point2D.Double A[] = new Point2D.Double[n];
        for (int i = 0; i < X.length; i++) {
            A[i] = new Point2D.Double(X[i], Y[i]);
        }

        // xを昇順ソートしておく
        Arrays.sort(A, new Comparator<Point2D.Double>() {
            @Override
            public int compare(Point2D.Double o1, Point2D.Double o2) {
                return (int)(o1.getX() - o2.getX());
            }
        });
        Arrays.stream(A).forEach(a -> {
            logger.info("x, y : ({}, {})", a.getX(), a.getY());
        });

        double ans = closest_pair(A, n);
        return ans;
    }

    /**
     * @param a x座標の昇順になっている配列
     * @param n
     * @return
     */
    private double closest_pair(Point2D.Double[] a, int n) {
        //logger.info("n count : {}", n);
        if (n <= 1) return Double.POSITIVE_INFINITY;
        int m = n / 2;
        double x = a[m].getX();
        Point2D.Double[] a1 = Arrays.copyOfRange(a, 0, m);
        Point2D.Double[] a2 = Arrays.copyOfRange(a, m, n - m);

        double d = Math.min(closest_pair(a1, m), closest_pair(a2, n - m));

        // yの昇順にする
        Arrays.sort(a, new Comparator<Point2D.Double>() {
            @Override
            public int compare(Point2D.Double o1, Point2D.Double o2) {
                return (int)(o1.getY() - o2.getY());
            }
        });

        // 距離d未満の頂点を取得していく
        List<Point2D.Double> b = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (Math.abs(a[i].getX() - x) >= d) continue;

            for (int j = 0; j < b.size(); j++) {
                double dx = a[i].getX() - b.get(b.size() - j - 1).getX();
                double dy = a[i].getY() - b.get(b.size() - j - 1).getY();
                if (dy >= d) break;

                d = Math.min(d, Math.sqrt(dx * dx + dy * dy));
            }
            b.add(a[i]);
        }
        return d;
    }
}
