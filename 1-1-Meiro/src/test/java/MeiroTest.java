import org.junit.Assert;
import org.junit.Test;

public class MeiroTest {
    // replace by your implementation class here
    private Meiro yourImplementation = new MeiroAnswerExample();

    // The expected value is normally hidden on actual programming contest
    private static int ANSWER = 22;

    @Test
    public void meiroTest1() {
        char B = '#'; // wall
        char O = '.'; // aisle

        char[][] meiro =
                {
                        { B,O,B,B,B,B,B,B,O,B },
                        { O,O,O,O,O,O,B,O,O,B },
                        { O,B,O,B,B,O,B,B,O,B },
                        { O,B,O,O,O,O,O,O,O,O },
                        { B,B,O,B,B,O,B,B,B,B },
                        { O,O,O,O,B,O,O,O,O,B },
                        { O,B,B,B,B,B,B,B,O,B },
                        { O,O,O,O,B,O,O,O,O,O },
                        { O,B,B,B,B,O,B,B,B,O },
                        { O,O,O,O,B,O,O,O,O,B }
                };

        long start = System.nanoTime();
        Assert.assertEquals(ANSWER, yourImplementation.bfs(meiro, 10, 10, 0, 1, 9, 8));
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

}