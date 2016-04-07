package main;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AnonymousSequenceSolverTest {
  private long startTime;

  private IAnonymousSequenceSolver yourImplementation = new AnonymousSequenceSolverExample();

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
    int[] a = new int[] {2, 2, 3, 4, 4, 5, 5};

    assertEquals(3, this.yourImplementation.solve(n, k, a));
  }

}
