import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberSetsTest {
  private long startTime;

  private NumberSetsInterface yourImplementation = new NumberSetsExample();

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
    assertEquals(9, this.yourImplementation.solve(10, 20, 5));
  }

  @Test
  public void test2() {
    assertEquals(7, this.yourImplementation.solve(10, 20, 3));
  }
}
