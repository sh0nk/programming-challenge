package main;

import org.junit.Assert;
import org.junit.Test;

public class TravelingByStagecoachTest {
    // replace by your implementation class here
    private TravelingByStagecoachInterface yourImplementation = new TravelingByStagecoach();

    @Test
    public void test1() {
        TravelingByStagecoachWrapper solver =
                new TravelingByStagecoachWrapper(2, 4, 2, 1, new int[]{3, 1});
        timeMeasuredAssertEquals(3.667, solver);
    }

    private class TravelingByStagecoachWrapper {
        private int n;
        private int m;
        private int a;
        private int b;
        private int[] t;

        public TravelingByStagecoachWrapper(int n, int m, int a, int b, int[] t) {
            this.n = n;
            this.m = m;
            this.a = a;
            this.b = b;
            this.t = t;
        }

        double solve() {
            return yourImplementation.solve(this.n, this.m, this.a, this.b, this.t);
        }
    }

    private void timeMeasuredAssertEquals(double expected, TravelingByStagecoachWrapper solver) {
        long start = System.nanoTime();

        double results = solver.solve();
        Assert.assertEquals(expected, results, 0.001);
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }
}
