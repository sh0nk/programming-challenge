import org.junit.Assert;
import org.junit.Test;

public class LatticeTest {
    // replace by your implementation class here
    private Lattice yourImplementation = new LatticeExample();

    @Test
    public void latticeTest1() {
        LatticeSolverWrapper solver = new LatticeSolverWrapper(1, 11, 5, 3);
        timeMeasuredAssertEquals(3, solver);
    }

    @Test
    public void latticeTest2() {
        LatticeSolverWrapper solver = new LatticeSolverWrapper(0, 0, 1, 3);
        timeMeasuredAssertEquals(0, solver);
    }

    @Test
    public void latticeTest3() {
        LatticeSolverWrapper solver = new LatticeSolverWrapper(3, 8, 3000, 8000);
        timeMeasuredAssertEquals(998, solver);
    }

    @Test
    public void latticeTest4() {
        LatticeSolverWrapper solver = new LatticeSolverWrapper(5, 3, -3, -1);
        timeMeasuredAssertEquals(3, solver);
    }

    @Test
    public void latticeTest5() {
        LatticeSolverWrapper solver = new LatticeSolverWrapper(0, 0, 6486480, 4194304);
        timeMeasuredAssertEquals(15, solver);
    }
    
    @Test
    public void latticeTest6() {
        LatticeSolverWrapper solver = new LatticeSolverWrapper(6486480, 4194304, 6486480, 4194304);
        timeMeasuredAssertEquals(0, solver);
    }

    private void timeMeasuredAssertEquals(int expected, LatticeSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

    private class LatticeSolverWrapper {
        int aX, aY, bX, bY;

        LatticeSolverWrapper(int aX, int aY, int bX, int bY) {
            this.aX = aX;
            this.aY = aY;
            this.bX = bX;
            this.bY = bY;
        }

        int solve() {
            return yourImplementation.solve(aX, aY, bX, bY);
        }
    }

}