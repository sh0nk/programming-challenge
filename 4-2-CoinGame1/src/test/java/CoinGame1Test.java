import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoinGame1Test {
  private static final long TIMEOUT_MILLISEC = 20000;
  private long startTime;

  private CoinGame1Interface yourImplementation = new CoinGame1Example();

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

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test1() {
    int x = 9;
    int k = 2;
    int[] a = new int[] { 1, 4 };
    assertEquals("Alice", this.yourImplementation.solve(x, k, a));
  }

}
