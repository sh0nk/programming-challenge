import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberSetsTest {
  private static final long TIMEOUT_MILLISEC = 2000;
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

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test1() {
    assertEquals(9, this.yourImplementation.solve(10, 20, 5));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test2() {
    assertEquals(7, this.yourImplementation.solve(10, 20, 3));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test3() {
    assertEquals(443, this.yourImplementation.solve(7, 2_000, 10));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test4() {
    assertEquals(1997, this.yourImplementation.solve(443, 20_000, 7));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test5() {
    assertEquals(1997, this.yourImplementation.solve(1997, 200_000, 443));
  }

    @Test(timeout = TIMEOUT_MILLISEC)
    public void test6() {
        assertEquals(2, this.yourImplementation.solve(200, 204, 2));
    }
}
