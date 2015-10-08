import org.junit.Assert;
import org.junit.Test;

public class CrazyRowsTest {
    // replace by your implementation class here
    private CrazyRows yourImplementation = new CrazyRowsExample();

    @Test
    public void CrazyRowsTest1() {
        int n = 2;
        int v[][] = {{1,0},{1,1}};

        CrazyRowsSolverWrapper solver = new CrazyRowsSolverWrapper(n, v);
        timeMeasuredAssertEquals(0, solver);
    }

    @Test
    public void CrazyRowsTest2() {
        int n = 3;
        int v[][] = {{0,0,1}, {1,0,0}, {0,1,0}};
        CrazyRowsSolverWrapper solver = new CrazyRowsSolverWrapper(n, v);
        timeMeasuredAssertEquals(2, solver);
    }

    @Test
    public void CrazyRowsTest3() {
        int n = 4;
        int v[][] = {{1,1,1,0},{1,1,0,0},{1,1,0,0},{1,0,0,0}};
        CrazyRowsSolverWrapper solver = new CrazyRowsSolverWrapper(n, v);
        timeMeasuredAssertEquals(4, solver);
        }

    private void timeMeasuredAssertEquals(int expected, CrazyRowsSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

    private class CrazyRowsSolverWrapper {
        int V[][];
        int N;

        CrazyRowsSolverWrapper(int N, int[][] V) {
            this.N = N;
            this.V = V;
        }

        int solve() {
            return yourImplementation.solve(N, V);
        }
    }

}