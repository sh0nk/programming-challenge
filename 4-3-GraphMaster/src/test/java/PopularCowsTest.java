import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PopularCowsTest {
  private long startTime;

  private PopularCowsInterface yourImplementation = new PopularCowsExample();

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
    int N = 3;
    int M = 3;
    int[] A = {1, 2, 2};
    int[] B = {2, 1, 3};
    assertEquals(1, this.yourImplementation.solve(N, M, A, B));
  }

  @Test
  public void test2() {
    int N = 6;
    int M = 7;
    int[] A = {1, 2, 2, 3, 4, 5, 6};
    int[] B = {3, 1, 5, 4, 5, 6, 4};
    assertEquals(3, this.yourImplementation.solve(N, M, A, B));
  }

}
