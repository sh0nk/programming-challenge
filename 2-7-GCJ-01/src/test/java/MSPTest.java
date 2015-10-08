import org.junit.Assert;
import org.junit.Test;

public class MSPTest {
    // replace by your implementation class here
    private MSP yourImplementation = new MSPExample();

    @Test
    public void MSPTest1() {
        int a1[] = {1, 3, -5};
        int a2[] = {-2, 4, 1};
        MSPSolverWrapper solver = new MSPSolverWrapper(a1, a2);
        timeMeasuredAssertEquals(-25, solver);
    }

    @Test
    public void MSPTest2() {
        int a1[] = {1,2,3,4,5};
        int a2[] = {1,0,1,0,1};
        MSPSolverWrapper solver = new MSPSolverWrapper(a1, a2);
        timeMeasuredAssertEquals(6, solver);
    }

    @Test
    public void MSPTest3() {
        int a1[] = {-2,2};
        int a2[] = {2,2,};
        MSPSolverWrapper solver = new MSPSolverWrapper(a1, a2);
        timeMeasuredAssertEquals(0, solver);
    }

    private void timeMeasuredAssertEquals(int expected, MSPSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

    private class MSPSolverWrapper {
        int v1[], v2[];

        MSPSolverWrapper(int[] v1, int[] v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        int solve() {
            return yourImplementation.solve(v1, v2);
        }
    }

}