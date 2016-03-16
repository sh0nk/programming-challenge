import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CuttingGameTest {
  private static final long TIMEOUT_MILLISEC = 20000;
  private long startTime;

  private CuttingGameInterface yourImplementation = new CuttingGameExample();

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
    int w = 4;
    int h = 2;
    assertEquals("LOSE", this.yourImplementation.solve(w, h));
  }

}
