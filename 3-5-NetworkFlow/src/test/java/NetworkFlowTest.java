import org.junit.Assert;
import org.junit.Test;

public class NetworkFlowTest {

  // replace by your implementation class here
  NetworkFlow yourImplementation = new NetworkFlowExample();

  @Test
  public void networkFlowTest1() {
    int[][] graph = new int[5][5];
    graph[0][1] = 10;
    graph[0][2] = 2;
    graph[1][2] = 6;
    graph[1][3] = 6;
    graph[2][4] = 5;
    graph[3][2] = 3;
    graph[3][4] = 8;

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(0, 4, graph);
    timeMeasuredAssertEquals(11, solver);
  }

  @Test
  public void networkFlowTest2() {
    //http://math.stackexchange.com/questions/840297/maximum-flow-ford-fulkerson
    int[][] graph = new int[8][8];
    graph[0][1] = 13;
    graph[0][2] = 10;
    graph[0][3] = 10;
    graph[1][4] = 24;
    graph[2][1] = 5;
    graph[2][3] = 15;
    graph[2][6] = 7;
    graph[3][6] = 15;
    graph[4][5] = 1;
    graph[4][7] = 9;
    graph[5][7] = 13;
    graph[5][6] = 6;
    graph[6][7] = 16;

    NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(0, 7, graph);
    timeMeasuredAssertEquals(25, solver);
  }


  private void timeMeasuredAssertEquals(int expected, NetworkFlowSolverWrapper solver) {
    long start = System.nanoTime();
    Assert.assertEquals(expected, solver.solve());
    long end = System.nanoTime();

    System.out
        .println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
  }

  private class NetworkFlowSolverWrapper {
    int S;
    int T;
    int[][] G;

    NetworkFlowSolverWrapper(int s, int t, int[][] g) {
      this.S = s;
      this.T = t;
      this.G = g;
    }

    int solve() {
      return yourImplementation.getMaxFow(S, T, G);
    }
  }
}
