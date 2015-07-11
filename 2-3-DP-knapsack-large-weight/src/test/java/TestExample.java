import org.junit.Assert;
import org.junit.Test;

public class TestExample {
    // replace by your implementation class here
    private Answer yourImplementation = new AnswerExample();

    // The expected value is normally hidden on actual programming contest
    private static int ANSWER = 7;

    @Test
    public void test1() {
        int[][] candidates = {{2,3}, {1,2}, {3,4}, {2,2}};

        long start = System.nanoTime();
        Assert.assertEquals(ANSWER, yourImplementation.solve(candidates, 4, 5));
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

}