package com.oop.task_2_1_1;

import java.util.Arrays;

public class ThreadPrimeCheck {
    //Returns the number of processors available to the Java virtual machine. (can be optional)
    //This method returns the maximum number of processors available to the virtual machine; never smaller than one
    static int THREADS = Runtime.getRuntime().availableProcessors();
    //Our prime numbers "flag".
    static boolean hasNotPrime = false;
    public static long[] arr;

    /**
     * @param array           to verify
     * @param numberOfThreads if it is needed, otherwise - all available processors
     * @return has it prime or not
     * @throws Exception
     */
    public static boolean threadRun(long[] array, int numberOfThreads) throws Exception {
        if (numberOfThreads > 0 && numberOfThreads < THREADS) {
            THREADS = numberOfThreads;
        }
        Thread[] t = new Thread[THREADS];
        arr = Arrays.copyOf(array, array.length);

        for (int i = 0; i < THREADS; i++) {
            t[i] = new Thread(new PrimeRun(i));
            t[i].start();
        }

        // to force one thread to wait for another thread to finish.
        for (int i = 0; i < THREADS; i++)
            t[i].join();

        return hasNotPrime;
    }

    public static long[] getArray() {
        return arr;
    }

    //synchronized
    //это ключевое слово, которое позволяет заблокировать доступ к методу, если его уже использует другой поток.
    public synchronized static void setHasNotPrime() {
        hasNotPrime = true;
    }
}

class PrimeRun implements Runnable {
    final long[] array = ThreadPrimeCheck.getArray();
    final int ID;

    public PrimeRun(int i) {
        ID = i;
    }

    public void run() {
        for (long l : array) {
            if (l % ThreadPrimeCheck.THREADS == ID && Validation.isNotPrime(l)) {
                ThreadPrimeCheck.setHasNotPrime();
                break;
            }
        }
    }
}
