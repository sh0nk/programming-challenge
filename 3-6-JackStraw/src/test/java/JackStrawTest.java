
import org.junit.Assert;
import org.junit.Test;

public class JackStrawTest {

  JackStrawInterface yourImplementation = new KenjiJackStrawExample();

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

  @Test
  public void jackStrawTest3() {
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
    int m = 10000;
    int[][] ab = new int[m][2];
    String expected[] = new String[m];
    for (int i = 0; i < m; i++) {
      switch (i % 4) {
        case 0:
          ab[i] = new int[] {1, 2};
          expected[i] = "CONNECTED";
          break;
        case 1:
          ab[i] = new int[] {2, 3};
          expected[i] = "NOT CONNECTED";
        case 2:
          ab[i] = new int[] {3, 4};
          expected[i] = "CONNECTED";
        case 3:
          ab[i] = new int[] {2, 4};
          expected[i] = "NOT CONNECTED";
      }
    }

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    timeMeasuredAssertEquals(expected, solver);
  }

  @Test
  public void jackStrawTest4() {
    int n = 12;
    int[][] p = {
        {0, 2}, {8, 5}, {2, 3}, {4, 4}, {6, 4}, {9, 4}, {0, 4}, {8, 2}, {5, 4}, {3, 4}, {9, 1},
        {6, 6},
    };
    int[][] q = {
        {2, 4}, {10, 5}, {5, 2}, {6, 3}, {7, 6}, {10, 6}, {3, 1}, {10, 3}, {4, 2}, {2, 1}, {8, 3},
        {8, 5},
    };
    int m = 16;
    int[][] ab =
        new int[][] {{1, 2}, {3, 5}, {4, 7}, {6, 9}, {10, 3}, {6, 10}, {7, 5}, {3, 12},
            {11, 6}, {4, 11}, {8, 1}, {10, 4}, {8, 11}, {9, 3}, {12, 2}, {6, 12}};
    String expected[] =
        new String[] {"NOT CONNECTED", "NOT CONNECTED", "CONNECTED", "NOT CONNECTED", "CONNECTED",
            "NOT CONNECTED", "NOT CONNECTED", "NOT CONNECTED", "NOT CONNECTED", "NOT CONNECTED",
            "NOT CONNECTED", "CONNECTED", "CONNECTED", "CONNECTED", "CONNECTED", "CONNECTED"};


    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    timeMeasuredAssertEquals(expected, solver);
  }
  
  @Test
  public void jackStrawTestKenji1() {
    int[][] p = {
        {0, 0},
        {0, 1},
        {1, 1},
        {1, 0},
        {0, 0},
        {0, 1}
    };
    int[][] q = {
        {0, 1},
        {1, 1},
        {1, 0},
        {0, 0},
        {1, 1},
        {1, 0}
    };
    int n = p.length;
    int[][] ab = {
        {1, 1},
        {1, 2},
        {1, 3},
        {1, 4},
        {1, 5},
        {1, 6},
        {2, 2},
        {2, 3},
        {2, 4},
        {2, 5},
        {2, 6},
        {3, 3},
        {3, 4},
        {3, 5},
        {3, 6},
        {4, 4},
        {4, 5},
        {4, 6},
        {5, 5},
        {5, 6},
        {6, 6}
    };
    int m = ab.length;

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    String[] expected = {
        "CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED",
        "CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED",
        "CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED","CONNECTED",
    };
    timeMeasuredAssertEquals(expected, solver);
  }
  

  @Test
  public void jackStrawTestKenji2() {
    int[][] p = {
        {0, 0},
        {1, 0},
        {2, 0}
    };
    int[][] q = {
        {1, 0},
        {2, 0},
        {3, 0}
    };
    int n = p.length;
    int[][] ab = {
        {1, 1},
        {1, 2},
        {1, 3},
        {2, 2},
        {2, 3},
        {3, 3},
    };
    int m = ab.length;

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    String[] expected = {
        "CONNECTED","CONNECTED","CONNECTED",
        "CONNECTED","CONNECTED","CONNECTED",
    };
    timeMeasuredAssertEquals(expected, solver);
  }

  @Test
  public void jackStrawTestKenji3() {
    int[][] p = {
        {0, 0},
        {1, 0},
        {2, 0},
        {3, 0}
    };
    int[][] q = {
        {2, 0},
        {3, 0},
        {4, 0},
        {5, 0}
    };
    int n = p.length;
    int[][] ab = {
        {1, 1},
        {1, 2},
        {1, 3},
        {1, 4},
        {2, 2},
        {2, 3},
        {2, 4},
        {3, 3},
        {3, 4},
        {4, 4},
    };
    int m = ab.length;

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
    String[] expected = {
        "CONNECTED","CONNECTED","CONNECTED",
        "CONNECTED","CONNECTED","CONNECTED",
        "CONNECTED","CONNECTED","CONNECTED","CONNECTED",
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
