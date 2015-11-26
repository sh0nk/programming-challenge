package main;

import org.junit.Assert;
import org.junit.Test;


public class SwapCountTest {

  // replace by your implementation class here
  private SwapCountInterface yourImplementation = new SwapCountExample();

  @Test
  public void swapCountTest1() {
    SwapCountSolverWrapper solver = new SwapCountSolverWrapper(4, new int[] {3, 1, 4, 2});
    timeMeasuredAssertEquals(3, solver);
  }

  private class SwapCountSolverWrapper {
    private int n;
    private int[] a;

    public SwapCountSolverWrapper(int n, int[] a) {
      this.n = n;
      this.a = a;
    }

    int solve() {
      return yourImplementation.solve(this.n, this.a);
    }
  }

  private void timeMeasuredAssertEquals(int expected, SwapCountSolverWrapper solver) {
    long start = System.nanoTime();
    Assert.assertEquals(expected, solver.solve());
    long end = System.nanoTime();

    System.out
        .println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
  }
}
