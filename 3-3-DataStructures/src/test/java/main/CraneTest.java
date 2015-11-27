package main;

import org.junit.Assert;
import org.junit.Test;

public class CraneTest {
  // replace by your implementation class here
  private CraneInterface yourImplementation = new CraneSh0nk();

  @Test
  public void craneTest1() {
    CraneSolverWrapper solver =
        new CraneSolverWrapper(2, 1, new int[] {10, 5}, new int[] {1}, new int[] {90});
    timeMeasuredAssertEquals(new double[][] {{5.0, 10.0}}, solver);
  }

  @Test
  public void craneTest2() {
    CraneSolverWrapper solver =
        new CraneSolverWrapper(3, 2, new int[] {5, 5, 5}, new int[] {1, 2}, new int[] {270, 90});
    timeMeasuredAssertEquals(new double[][] {{-10.0, 5.0}, {-5.0, 10.0}}, solver);
  }

    @Test
    public void craneTest3() {
        CraneSolverWrapper solver =
                new CraneSolverWrapper(3, 3, new int[] {5, 5, 5}, new int[] {1, 2, 1}, new int[] {270, 90, 90});
        timeMeasuredAssertEquals(new double[][] {{-10.0, 5.0}, {-5.0, 10.0}, {5.0, 0.0}}, solver);
    }

  private class CraneSolverWrapper {
    private int N;
    private int C;
    private int[] L;
    private int[] S;
    private int[] A;

    public CraneSolverWrapper(int N, int C, int[] L, int[] S, int[] A) {
      this.N = N;
      this.C = C;
      this.L = L;
      this.S = S;
      this.A = A;
    }

    double[][] solve() {
      return yourImplementation.solve(this.N, this.C, this.L, this.S, this.A);
    }
  }

  private void timeMeasuredAssertEquals(double[][] expected, CraneSolverWrapper solver) {
    long start = System.nanoTime();

    double[][] results = solver.solve();
    for (int i = 0; i < expected.length; i++) {
      Assert.assertArrayEquals(expected[i], results[i], 0);
    }
    long end = System.nanoTime();

    System.out
        .println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
  }
}
