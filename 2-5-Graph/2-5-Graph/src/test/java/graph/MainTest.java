package graph;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainTest {
  private Solvable s;

  @Before
  public void setUp() {
    this.s = new Main();
  }

  @Test
  public void testSolve1() {
    int result = this.s.solve(5, 5, new int[][] {{4, 3, 6831}, {1, 3, 4583}, {0, 0, 6592},
        {0, 1, 3063}, {3, 3, 4975}, {1, 3, 2049}, {4, 2, 2104}, {2, 2, 781}});
    assertEquals(result, 71071);
  }

  @Test
  public void testSolve2() {
    int result = this.s.solve(7, 3, new int[][] {});
    assertEquals(result, 100000);
  }

}
