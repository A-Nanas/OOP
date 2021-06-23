

// Sequential run of prime check.

public class SimplePrimeCheck {
    //Prime numbers flag.
    static boolean hasNotPrime = false;

    public static boolean sequentialRun(long[] array) {
        for (int i = 0; i < array.length; i++) {
            if (Validation.isNotPrime(array[i])) {
                hasNotPrime = true;
                break;
            }
        }
        /**
        @return true if the number is not prime, else false
         */
        return hasNotPrime;
    }
}


