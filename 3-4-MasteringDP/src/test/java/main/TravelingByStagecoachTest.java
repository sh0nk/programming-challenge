package main;

import org.junit.Assert;
import org.junit.Test;

public class TravelingByStagecoachTest {
  // replace by your implementation class here
  private TravelingByStagecoachInterface yourImplementation =
      new TravelingByStagecoach();

  @Test
  public void test1() {
    TravelingByStagecoachWrapper solver =
        new TravelingByStagecoachWrapper(2, 4, 2, 1, new int[] {3, 1}, new int[][] {
            {0, 0, 3, 2},
            {0, 0, 3, 5},
            {3, 3, 0, 0},
            {2, 5, 0, 0}
        });
    timeMeasuredAssertEquals(3.667, solver);
  }

  @Test
  public void test2() {
    TravelingByStagecoachWrapper solver =
        new TravelingByStagecoachWrapper(4, 5, 4, 5, new int[] {3, 3, 4, 2}, new int[][] {
            {0, 0, 3, 2, 0},
            {0, 0, 4, 0, 3},
            {3, 4, 0, 7, 0},
            {2, 0, 7, 0, 20},
            {0, 3, 0, 20, 0},
        });
    timeMeasuredAssertEquals(4, solver);
  }

  private class TravelingByStagecoachWrapper {
    private int n;
    private int m;
    private int a;
    private int b;
    private int[] t;
    private int[][] d;

    public TravelingByStagecoachWrapper(int n, int m, int a, int b, int[] t, int[][] d) {
      this.n = n;
      this.m = m;
      this.a = a;
      this.b = b;
      this.t = t;
      this.d = d;
    }

    double solve() {
      return yourImplementation.solve(this.n, this.m, this.a, this.b, this.t, this.d);
    }
  }

  private void timeMeasuredAssertEquals(double expected, TravelingByStagecoachWrapper solver) {
    long start = System.nanoTime();

    double results = solver.solve();
    Assert.assertEquals(expected, results, 0.001);
    long end = System.nanoTime();

    System.out
        .println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
  }
}
