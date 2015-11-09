import org.junit.Assert;
import org.junit.Test;

/**
 * <h1>4 Values whose Sum is 0(POJ No.2785)</h1>
 * 
 * <pre>
 * ￼￼￼要素数nの整数のリストA, B, C,Dが与えられます。各リストから1つずつ取り出したとき、その和が0
 * となるような組み合わせの個数を求めなさい。ただし、1つのリストに同じ値が複数個含まれている場合、 そ
 * れらは異なるものとして数えます。
 * 
 * 制約
 * ・1 ≦ n ≦ 4000
 * ・|(リストの値)| ≦ 228
 * </pre>
 */
public class FourValuesWhoseSumIsZeroTest {

    // replace by your implementation class here
    private FourValuesWhoseSumIsZeroI yourImplementation = new FourValuesWhoseSumIsZeroExample();

    @Test
    public void cableMasterTest1() {
        int n = 6;
        int[] A = { -45, -41, -36, -36, 26, -32 };
        int[] B = { 22, -27, 53, 30, -38, -54 };
        int[] C = { 42, 56, -37, -75, -10, -6 };
        int[] D = { -16, 30, 77, -46, 62, 45 };

        SolverWrapper solver = new SolverWrapper(n, A, B, C, D);
        timeMeasuredAssertEquals(5, solver);
    }

    private void timeMeasuredAssertEquals(Object expected, SolverWrapper solver) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, solver.solve());
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }

    private class SolverWrapper {
        int n;
        int[] A;
        int[] B;
        int[] C;
        int[] D;

        SolverWrapper(int n, int[] A, int[] B, int[] C, int[] D) {
            this.n = n;
            this.A = A;
            this.B = B;
            this.C = C;
            this.D = D;
        }

        int solve() {
            return yourImplementation.solve(n, A, B, C, D);
        }
    }

}
