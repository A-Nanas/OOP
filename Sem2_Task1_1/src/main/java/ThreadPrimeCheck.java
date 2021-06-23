import java.util.Arrays;

public class ThreadPrimeCheck {
    //Returns the number of processors available to the Java virtual machine. (can be optional)
    //This method returns the maximum number of processors available to the virtual machine; never smaller than one
    static int threads = Runtime.getRuntime().availableProcessors();
    //Our prime numbers "flag".
    private static boolean hasNotPrime = false;
    private static long[] arr;

    /**
     * @param array           to verify
     * @param numberOfThreads if it is needed, otherwise - all available processors
     * @return has it prime or not
     * @throws Exception
     */
    public static boolean threadRun(final long[] array, final int numberOfThreads) throws Exception {
        if (numberOfThreads > 0 && numberOfThreads < threads) {
            threads = numberOfThreads;
        }
        Thread[] t = new Thread[threads];
        arr = Arrays.copyOf(array, array.length);

        for (int i = 0; i < threads; i++) {
            t[i] = new Thread(new PrimeRun(i));
            t[i].start();
        }

        // to force one thread to wait for another thread to finish.
        for (int i = 0; i < threads; i++) {
            t[i].join();
        }

        return hasNotPrime;
    }

    public static long[] getArray() {
        return arr;
    }

    //synchronized
    //это ключевое слово, которое позволяет заблокировать доступ к методу, если его уже использует другой поток.
    public static synchronized void setHasNotPrime() {
        hasNotPrime = true;
    }
}

class PrimeRun implements Runnable {
    final long[] array = ThreadPrimeCheck.getArray();
    final int id;

    public PrimeRun(final int i) {
        id = i;
    }

    public void run() {
        for (long l : array) {
            if (l % ThreadPrimeCheck.threads == id && Validation.isNotPrime(l)) {
                ThreadPrimeCheck.setHasNotPrime();
                break;
            }
        }
    }
}
