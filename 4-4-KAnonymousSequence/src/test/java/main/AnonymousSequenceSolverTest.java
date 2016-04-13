package main;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnonymousSequenceSolverTest {
  private long startTime;

  private IAnonymousSequenceSolver yourImplementation = new KenjiAnonymousSequenceSolver();

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
    int n = 7;
    int k = 3;
    int[] a = new int[] { 2, 2, 3, 4, 4, 5, 5 };

    assertEquals(3, this.yourImplementation.solve(n, k, a));
  }

  @Test
  public void test2() {
    int n = 9;
    int k = 4;
    int[] a = new int[] { 2, 2, 3, 4, 4, 4, 5, 5, 5 };

    assertEquals(6, this.yourImplementation.solve(n, k, a));
  }

  @Test
  public void test3() {
    int n = 15;
    int k = 4;
    int[] a = new int[] { 2, 2, 3, 4, 5, 6, 6, 7, 7, 7, 8, 8, 9, 9, 9 };

      assertEquals(12, this.yourImplementation.solve(n, k, a));
  }

    @Test
    public void test4() {
        int n = 15;
        int k = 6;
        int[] a = new int[] { 2, 2, 3, 4, 5, 6, 6, 6, 7, 7, 8, 8, 9, 9, 9 };

        assertEquals(25, this.yourImplementation.solve(n, k, a));
    }


}
