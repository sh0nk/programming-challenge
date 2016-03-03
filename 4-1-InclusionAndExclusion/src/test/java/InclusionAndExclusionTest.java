import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InclusionAndExclusionTest {
  private static final long TIMEOUT_MILLISEC = 20000;
  private long startTime;

  private InclusionAndExclusionInterface yourImplementation = new InclusionAndExclusionNobby();

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
    assertEquals(67, this.yourImplementation.solve(100, 2, new int[] { 2, 3 }));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test2() {
    assertEquals(72, this.yourImplementation.solve(100, 3, new int[] { 2, 3, 7 }));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test3() {
    assertEquals(10, this.yourImplementation.solve(1000000, 1, new int[] { 100000 }));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test4() {
    assertEquals(6, this.yourImplementation.solve(6, 6, new int[] { 1, 2, 3, 4, 5, 6 }));
  }

  @Test(timeout = TIMEOUT_MILLISEC)
  public void test5() {
    assertEquals(24, this.yourImplementation.solve(1000000000, 15,
        new int[] { 999999999, 999999998, 999999997, 999999996, 999999995, 999999994, 99999993, 999999992, 999999991, 999999990, 999999989, 999999988, 999999987, 999999986, 999999985 }));
  }

}
