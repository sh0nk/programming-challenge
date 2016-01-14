import org.junit.Assert;
import org.junit.Test;

import java.security.spec.ECGenParameterSpec;
import java.util.HashMap;

public class NetworkFlowTest {
    // replace by your implementation class here
    NetworkFlow yourImplementation = new NetworkFlowExample();

    //HashMap<Integer, Edge[]> G = new HashMap<Integer, Edge[]>();
    //HashMap<Integer, String> hoge = new HashMap<Integer, String>();

    Edge edge00 = new Edge(1, 10, 0);
    Edge edge01 = new Edge(2, 2, 0);
    Edge edge0[] = {edge00, edge01};


    Edge edge10 = new Edge(2, 6, 1);
    Edge edge11 = new Edge(3, 6, 1);
    Edge edge1[] = {edge10, edge11};

    Edge edge20 = new Edge(4, 5, 2);
    Edge edge2[] = {edge20};

    Edge edge30 = new Edge(2, 3, 3);
    Edge edge31 = new Edge(4, 8, 3);
    Edge edge3[] = {edge30, edge31};

    //G.put(0, edge0);
    //G.put(1, edge1);
    //G.put(2, edge2);
    //G.put(3, edge3);
    HashMap<Integer, Edge[]> G = new HashMap<Integer, Edge[]>() {
        {
            put(0, edge0);
            put(1, edge1);
            put(2, edge2);
            put(3, edge3);
        }
    };

    @Test
    public void networkFlowTest1() {

        NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(0, 3, G);
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
        HashMap<Integer, Edge[]> G;

        NetworkFlowSolverWrapper(int s, int t, HashMap<Integer, Edge[]> g) {
            this.S = s;
            this.T = t;
            this.G = g;
        }

        int solve() {
            return yourImplementation.getMaxFow(S, T, G);
        }
    }
}