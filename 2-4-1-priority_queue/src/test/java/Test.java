import org.junit.Assert;

public class Test {

    private Interface yourImplementation = new AnswerSh0nk();

    @org.junit.Test
    public void test1() {
        final int ANSWER = 2;

        int N = 4;
        int L = 25;
        int P = 10;
        int[] A = new int[] { 10, 14, 20, 21 };
        int[] B = new int[] { 10, 5, 2, 4 };

        long start = System.nanoTime();
        Assert.assertEquals(ANSWER, yourImplementation.execute(N, L, P, A, B));
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }

    @org.junit.Test
    public void test2() {
        final int ANSWER = 10;

        int N = 10;
        int L = 65;
        int P = 10;
        int[] A = new int[] { 10, 14, 20, 21, 25, 30, 40, 44, 50, 60 };
        int[] B = new int[] { 10, 5, 2, 4, 13, 5, 6, 7, 2, 1 };

        long start = System.nanoTime();
        Assert.assertEquals(ANSWER, yourImplementation.execute(N, L, P, A, B));
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }


    @org.junit.Test
    public void test3() {
        final int ANSWER = -1;

        int N = 2;
        int L = 65;
        int P = 5;
        int[] A = new int[] { 10, 14 };
        int[] B = new int[] { 10, 5 };

        long start = System.nanoTime();
        Assert.assertEquals(ANSWER, yourImplementation.execute(N, L, P, A, B));
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis.", (end - start) / 1000000f));
    }

}
