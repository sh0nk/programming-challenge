import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DNARepairSolverTest {
  private long startTime;
  private IDNARepairSolver yourImplementation = new DNARepairSolverExample();
  private static final double DELTA = 1e-4;

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
    String S = "AAAG";
    String[] P = new String[] { "AAA", "AAG" };
    double answer = yourImplementation.solve(S, P);
    Assert.assertEquals(36.2215, answer, DELTA);
  }

  @Test
  public void test2() {
    String S = "TGAATG";
    String[] P = new String[] { "A", "TG" };
    double answer = yourImplementation.solve(S, P);
    Assert.assertEquals(36.2215, answer, DELTA);
  }

  @Test
  public void test3() {
    String S = "AGT";
    String[] P = new String[] { "A", "G", "C", "T" };
    double answer = yourImplementation.solve(S, P);
    Assert.assertEquals(36.2215, answer, DELTA);
  }
}
