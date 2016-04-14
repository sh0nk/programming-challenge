import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SquareDestroyerSolverTest {
  private long startTime;

  private ISquareDestroyerSolver yourImplementation = null;

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
        int n = 2;
        int[] excludes = new int[] {};

        assertEquals(3, this.yourImplementation.solve(n, excludes));
    }

    @Test
    public void test2() {
        int n = 3;
        int[] excludes = new int[] {12, 17, 23};

        assertEquals(3, this.yourImplementation.solve(n, excludes));
    }

}
