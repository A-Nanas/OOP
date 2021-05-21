import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrimeCheckTest {
    private final long[] testArray = {2147483647L, 514229L, 433494437L, 1336337L, 4477457L, 5308417L, 8503057L, 9834497L, 29986577L, 40960001L, 45212177L, 59969537L, 65610001L, 126247697L, 193877777L};
    private long[] enlargedTestArray = new long[100000];

    public void arrayIncreasing() {
        for (int i = 0; i < enlargedTestArray.length; i++) {
            enlargedTestArray[i] = testArray[i % testArray.length];
        }
    }

    @Test
    public void exampleFirstTest() {
        long[] exArray = {6, 8, 7, 13, 9, 4};

        assertTrue(SimplePrimeCheck.sequentialRun(exArray));
    }

    @Test
    public void exampleSecondTest() {
        long[] exArray = {6997901, 6997927, 6997937, 6997967, 6998009, 6998029, 6998039, 6998051, 6998053};

        assertFalse(SimplePrimeCheck.sequentialRun(exArray));
    }

    @Test
    public void simpleTest() {
        arrayIncreasing();
        assertFalse(SimplePrimeCheck.sequentialRun(enlargedTestArray));
    }

    @Test
    public void threadTest2() throws Exception {
        arrayIncreasing();
        assertFalse(ThreadPrimeCheck.threadRun(enlargedTestArray, 2));
    }

    @Test
    public void threadTest4() throws Exception {
        arrayIncreasing();
        assertFalse(ThreadPrimeCheck.threadRun(enlargedTestArray, 4));
    }

    @Test
    public void threadTest8() throws Exception {
        arrayIncreasing();
        assertFalse(ThreadPrimeCheck.threadRun(enlargedTestArray, 8));
    }

    @Test
    public void threadTest16() throws Exception {
        arrayIncreasing();
        assertFalse(ThreadPrimeCheck.threadRun(enlargedTestArray, 16));
    }

    @Test
    public void streamTest() {
        arrayIncreasing();
        int index = 0;
        Long[] testArrayLong = new Long[enlargedTestArray.length];
        for (final Long value : enlargedTestArray) {
            testArrayLong[index++] = value;
        }
        assertFalse(StreamPrimeCheck.streamRun(testArrayLong));
    }
}