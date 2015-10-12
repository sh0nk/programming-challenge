import org.junit.Assert;
import org.junit.Test;

public class CableMasterTest {
    // replace by your implementation class here
    private CableMaster yourImplementation = new CableMasterExample();
    private int N1 = 4;
    private int K1 = 11;
    private double[] L1 = {8.02, 7.43, 4.57, 5.39};
    
    private int N2 = 10;
    private int K2 = 27;
    private double[] L2 = {
    		18.02, 337.43, 234.57, 95.39, 95.39, 
    		495.09, 5542.33, 225.18, 45.89, 1107.32
    		};

    @Test
    public void cableMasterTest1() {
        CableMasterSolverWrapper solver = new CableMasterSolverWrapper(N1, K1, L1);
        timeMeasuredAssertEquals("2.00", solver);
    }
    
    @Test
    public void cableMasterTest2() {
        CableMasterSolverWrapper solver = new CableMasterSolverWrapper(N2, K2, L2);
        timeMeasuredAssertEquals("263.92", solver);
    }

	private void timeMeasuredAssertEquals(String expected, CableMasterSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

    private class CableMasterSolverWrapper {
        int N;
        int K;
        double[] L;

        CableMasterSolverWrapper(int N, int K, double[] L) {
            this.N = N;
            this.K = K;
            this.L = L;
        }

        String solve() {
            return yourImplementation.solve(N, K, L);
        }
    }

}