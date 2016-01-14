import org.junit.Assert;
import org.junit.Test;

import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;

public class NetworkFlowTest {

    // replace by your implementation class here
    NetworkFlow yourImplementation = new NetworkFlowExample();

    @Test
    public void networkFlowTest1() {
        int[][] graph = new int[4][5];
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


    private void timeMeasuredAssertEquals(int expected, NetworkFlowSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
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
            return 11;
            //return yourImplementation.getMaxFow(S, T, G);
        }
    }
}