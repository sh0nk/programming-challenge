
import org.junit.Assert;
import org.junit.Test;

public class JackStrawTest {

    JackStrawExample yourImplementation = new JackStrawExample();

    @Test
    public void jackStrawTest() {
        int n = 4;
        int[][] p = {
                { 0, 4 },
                { 0, 1 },
                { 1, 2 },
                { 1, 0 }
        };
        int[][] q = {
                { 4, 1 },
                { 2, 3 },
                { 3, 4 },
                { 2, 1 }
        };
        int m = 4;
        int[][] ab = {
                { 1, 2 },
                { 1, 4 },
                { 2, 3 },
                { 2, 4 }
        };
        int[][] graph = new int[5][5];
        graph[0][1] = 10;
        graph[0][2] = 2;
        graph[1][2] = 6;
        graph[1][3] = 6;
        graph[2][4] = 5;
        graph[3][2] = 3;
        graph[3][4] = 8;

        NetworkFlowSolverWrapper solver = new NetworkFlowSolverWrapper(n, p, q, m, ab);
        String[] expected = {
                "CONNECTED",
                "NOT CONNECTED",
                "CONNECTED",
                "NOT CONNECTED"
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
