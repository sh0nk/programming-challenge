import org.junit.Assert;
import org.junit.Test;

public class AggressiveCowsTest {
    // replace by your implementation class here
    private AggressiveCows yourImplementation = new AggressiveCowsExample();
    private int N1 = 5;
    private int M1 = 3;
    private int[] x1 = {1, 2, 8, 4, 9};
    
    @Test
    public void aggressiveCowsTest1() {
    	AggressiveCowsSolverWrapper solver = new AggressiveCowsSolverWrapper(N1, M1, x1);
    	timeMeasuredAssertEquals(3, solver);
    }

	private void timeMeasuredAssertEquals(int expected, AggressiveCowsSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

    private class AggressiveCowsSolverWrapper {
        int N;
        int M;
        int[] x;

        AggressiveCowsSolverWrapper(int N, int M, int[] x) {
            this.N = N;
            this.M = M;
            this.x = x;
        }

        int solve() {
            return yourImplementation.solve(N, M, x);
        }
    }

}