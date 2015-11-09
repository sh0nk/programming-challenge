import org.junit.Assert;
import org.junit.Test;

/**
 * <h1>Subsequence(POJ No.3061)</h1>
 * 
 * <pre>
 * 長さnの数列a0,a1,...,an-1と整数Sが与えられます。連続する部分列で、その総和がS以上となるようなもの
 * のうち、最小の長さを求めなさい。解が存在しない場合は 0 を出力しなさい。
 * 
 * 制約
 * ・10 < n < 10^5
 * ・0 < ai ≦ 10^4
 * ・S < 10^8
 * </pre>
 */
public class SubsequenceTest {
    // replace by your implementation class here
    private SubsequenceI yourImplementation = new SubsequenceExample();

    @Test
    public void cableMasterTest1() {
        int n = 10;
        int S = 15;
        int[] a = { 5, 1, 3, 5, 10, 7, 4, 9, 2, 8 };

        SubsequenceSolverWrapper solver = new SubsequenceSolverWrapper(n, S, a);
        timeMeasuredAssertEquals(2, solver);
    }

    @Test
    public void cableMasterTest2() {
        int n = 5;
        int S = 11;
        int[] a = { 1, 2, 3, 4, 5 };

        SubsequenceSolverWrapper solver = new SubsequenceSolverWrapper(n, S, a);
        timeMeasuredAssertEquals(3, solver);
    }

    private void timeMeasuredAssertEquals(Object expected, SubsequenceSolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }

    private class SubsequenceSolverWrapper {
        int N;
        int K;
        int[] L;

        SubsequenceSolverWrapper(int N, int K, int[] L) {
            this.N = N;
            this.K = K;
            this.L = L;
        }

        int solve() {
            return yourImplementation.solve(N, K, L);
        }
    }

}