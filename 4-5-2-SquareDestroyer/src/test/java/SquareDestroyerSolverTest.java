import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

  @Test
  public void test3() {
    int n = 2;
    int[] excludes = new int[] {5, 7, 10, 12};

    assertEquals(1, this.yourImplementation.solve(n, excludes));
  }

  @Test
  public void test4() {
    int n = 3;
    int[] excludes = new int[] {14, 17, 20, 21, 23, 24};

    assertEquals(4, this.yourImplementation.solve(n, excludes));
  }

  @Test
  public void test5() {
    int n = 4;
    int[] excludes = new int[] {14, 17, 20, 21, 23, 24};

    assertEquals(5, this.yourImplementation.solve(n, excludes));
  }

}
