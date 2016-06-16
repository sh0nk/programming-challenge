import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClosestPairSolverTest {
    private long startTime;
    private IClosestPairSolver yourImplementation = new ClosestPairSolverExample();
    private static final double DELTA = 1e-4;

    @Before
    public void setUp() {
        this.startTime = System.nanoTime();
    }

    @After
    public void tearDown() {
        double timeElapsed = (System.nanoTime() - this.startTime) / 1_000_000f;
        System.out.println(
                String.format("Finished execution in: %f millis.", timeElapsed));
    }

    @Test
    public void test1() {
        int n = 5;
        double[] x = {0, 6, 43, 39, 189};
        double[] y = {2, 67, 71, 107, 140};

        double answer = yourImplementation.solve(n, x, y);
        Assert.assertEquals(36.2215, answer, DELTA);
    }
}
