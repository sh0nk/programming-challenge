import org.junit.Assert;
import org.junit.Test;

public class LatticeTest {
    // replace by your implementation class here
    private Lattice yourImplementation = new LatticeExample();

    @Test
    public void latticeTest1() {
        timeMeasuredAssertEquals(3, yourImplementation.solve(1, 11, 5, 3));
    }

    @Test
    public void latticeTest2() {
        timeMeasuredAssertEquals(0, yourImplementation.solve(0, 0, 1, 3));
    }

    @Test
    public void latticeTest3() {
        timeMeasuredAssertEquals(998, yourImplementation.solve(3, 8, 3000, 8000));
    }

    @Test
    public void latticeTest4() {
        timeMeasuredAssertEquals(3, yourImplementation.solve(5, 3, -3, -1));
    }

    @Test
    public void latticeTest5() {
        timeMeasuredAssertEquals(2310143, yourImplementation.solve(0, 0, 540573696, 2310144));
    }


    private void timeMeasuredAssertEquals(int expected, int actual) {
        long start = System.nanoTime();
        Assert.assertEquals(expected, actual);
        long end = System.nanoTime();

        System.out.println(String.format("Finished execution in: %f millis." , (end - start) / 1000000f ));
    }

}