import org.junit.Assert;

public class Test {

    private Interface yourImplementation = null;

    @org.junit.Test
    public void test1() {
        final int ANSWER = 3;

        int N = 100;
        int K = 7;
        int[] type = new int[] { 1, 2, 2, 2, 1, 2, 1 };
        int[] x = new int[] { 101, 1, 2, 3, 1, 3, 5 };
        int[] y = new int[] { 1, 2, 3, 3, 3, 1, 5 };

        long start = System.nanoTime();
        Assert.assertEquals(ANSWER, yourImplementation.execute(N, K, type, x, y));
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }


}
