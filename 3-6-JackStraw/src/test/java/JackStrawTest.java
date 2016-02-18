
import org.junit.Assert;
import org.junit.Test;

public class JackStrawTest {

  JackStrawInterface yourImplementation = new JackStrawExample();

  @Test
  public void jackStrawTest1() {
    int n = 4;
    int[][] p = {
        {0, 4},
        {0, 1},
        {1, 2},
        {1, 0}
    };
    int[][] q = {
        {4, 1},
        {2, 3},
        {3, 4},
        {2, 1}
    };
    int m = 4;
    int[][] ab = {
        {1, 2},
        {1, 4},
        {2, 3},
        {2, 4}
    };

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    String[] expected = {
        "CONNECTED",
        "NOT CONNECTED",
        "CONNECTED",
        "NOT CONNECTED"
    };
    timeMeasuredAssertEquals(expected, solver);
  }

  @Test
  public void jackStrawTest2() {
    int n = 4;
    int[][] p = {
        {0, 0},
        {0, 2},
        {2, 0},
        {3, 2}
    };
    int[][] q = {
        {2, 2},
        {2, 0},
        {4, 2},
        {5, 0}
    };
    int m = 4;
    int[][] ab = {
        {1, 2},
        {2, 3},
        {3, 4},
        {2, 4}
    };

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    String[] expected = {
        "CONNECTED",
        "CONNECTED",
        "CONNECTED",
        "CONNECTED"
    };
    timeMeasuredAssertEquals(expected, solver);
  }

  private void timeMeasuredAssertEquals(String[] expected, NetworkFlowSolverWrapper solver) {
    long start = System.nanoTime();
    Assert.assertArrayEquals(expected, solver.solve());
    long end = System.nanoTime();

    System.out.println(
        String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
  }

  private class NetworkFlowSolverWrapper {
    int n;
    int[][] p;
    int[][] q;
    int m;
    int ab[][];

    NetworkFlowSolverWrapper(int n, int[][] p, int[][] q, int m, int ab[][]) {
      this.n = n;
      this.p = p;
      this.q = q;
      this.m = m;
      this.ab = ab;
    }

    String[] solve() {
      return yourImplementation.solve(n, p, q, m, ab);
    }
  }

}
