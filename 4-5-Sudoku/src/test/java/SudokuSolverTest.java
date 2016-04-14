import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class SudokuSolverTest {
  private long startTime;

  private ISudokuSolver yourImplementation = null;

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
        int[][] a = new int[][]{
                {0, 0, 0, 0, 0, 0, 5, 2, 0},
                {0, 8, 0, 4, 0, 0, 0, 0, 0},
                {0, 3, 0, 0, 0, 9, 0, 0, 0},
                {5, 0, 1, 0, 0, 0, 6, 0, 0},
                {2, 0, 0, 7, 0, 0, 0, 0, 0},
                {0, 0, 0, 3, 0, 0, 0, 0, 0},
                {6, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 7, 0, 4},
                {0, 0, 0, 0, 0, 0, 0, 3, 0}
        };
        int[][] answer = new int[][]{
                {4, 1, 6, 8, 3, 7, 5, 2, 9},
                {9, 8, 2, 4, 6, 5, 3, 7, 1},
                {7, 3, 5, 1, 2, 9, 4, 6, 8},
                {5, 7, 1, 2, 9, 8, 6, 4, 3},
                {2, 9, 3, 7, 4, 6, 1, 8, 5},
                {8, 6, 4, 3, 5, 1, 2, 9, 7},
                {6, 4, 7, 9, 1, 3, 8, 5, 2},
                {3, 5, 9, 6, 8, 2, 7, 1, 4},
                {1, 2, 8, 5, 7, 4, 9, 3, 6}
        };

        int[][] actual = this.yourImplementation.solve(a);
        for (int i = 0; i < a.length; i++) {
            assertArrayEquals(answer[i], actual[i]);
        }
    }



}
