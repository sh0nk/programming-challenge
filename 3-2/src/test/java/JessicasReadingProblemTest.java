import org.junit.Assert;
import org.junit.Test;

/**
 * <h1>Jessica's Reading Problem(POJ No.3320)</h1>
 * 
 * <pre>
 * Jessicaは試験に向けて分厚い教科書を読むことにしました。試験に合格するためには、この本の中に書か
 * れている事柄をすべてマスターしなくてはなりません。この本は P ページからなり、i ページ目にはちょう
 * ど 1 つの事柄 a iについて書かれています(整数の ID が付けられています)。本の中には同じ事柄が何度も書
 * かれているので、彼女はこの本の中の連続するページですべての事柄をカバーしているような部分を読むこ
 * とにしました。各ページに書かれている事柄が与えられるので、読まなければいけない最小のページ数を求
 * めなさい。
 * 
 * 制約
 * ・1 ≦ P ≦ 10^6
 * </pre>
 *
 */
public class JessicasReadingProblemTest {

    // replace by your implementation class here
    private JessicasReadingProblemI yourImplementation = new JessicasReadingProblemExample();

    @Test
    public void cableMasterTest1() {
        int P = 10;
        int[] a = { 1, 8, 8, 8, 1 };

        SolverWrapper solver = new SolverWrapper(P, a);
        timeMeasuredAssertEquals(2, solver);
    }

    private void timeMeasuredAssertEquals(Object expected, SolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }

    private class SolverWrapper {
        int P;
        int[] a;

        SolverWrapper(int P, int[] a) {
            this.P = P;
            this.a = a;
        }

        int solve() {
            return yourImplementation.solve(P, a);
        }
    }

}
