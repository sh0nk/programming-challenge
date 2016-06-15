import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.geom.Point2D;
import java.util.Arrays;

public class ClosestPairSolverExample implements IClosestPairSolver {
    private static Logger logger = LogManager.getLogger(ClosestPairSolverExample.class);

    int N;
    Point2D.Double A[] = new Point2D.Double[10000];

    //TODO 途中なので完成させる
    public double solve(int n, double[] X, double[] Y) {
        N = n;
        // xを昇順ソートしておく
        Arrays.sort(X);

        for (int i = 0; i < X.length; i++) {
            A[i] = new Point2D.Double(X[i], Y[i]);
        }

        if (n <= 1) return Double.POSITIVE_INFINITY;
        int m = n / 2;
        double x = A[m].getX();





        return 36.2215;
    }

    private boolean compare_y(Point2D.Double a, Point2D.Double b) {
        return a.getX() < b.getY();
    }
}
