import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HousewifeWindTest {
  private long startTime;

  private HousewifeWindInterface yourImplementation = new HousewifeWindExample();

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
    int n = 3;
    int q = 3;
    int s = 1;
    ArrayList<int[]> abw = new ArrayList<>();
    int[] abw1 = {1, 2, 1};
    int[] abw2 = {2, 3, 2};
    abw.add(abw1);
    abw.add(abw2);

    List<List> query = new ArrayList<>();
    query.add(Arrays.asList("A", 2));
    query.add(Arrays.asList("B", 2, 3));
    query.add(Arrays.asList("A", 3));

    assertEquals("1\n3\n", this.yourImplementation.solve(n, q, s, abw, query));
  }
}
