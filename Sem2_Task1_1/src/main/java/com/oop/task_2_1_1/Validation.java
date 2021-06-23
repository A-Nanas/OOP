package com.oop.task_2_1_1;

public class Validation {
    /**
     * @param n is our number
     * @return true if it does not satisfy the conditions of a non-Prime number
     */
    private static boolean isPrime(final long n) {
        if (n == 2 || n == 3 || n == 5) {
            return true;
        }
        if (n <= 1 || (n & 1) == 0) {
            return false;
        }

        for (int i = 3; (long) i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNotPrime(final long n) {
        return !isPrime(n);
    }
}
