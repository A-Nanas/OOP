public class SimplePrimeCheck {
    //Our prime numbers "flag".
    static boolean hasNotPrime = false;

    public static boolean sequentialRun(long[] array) {
        for (int i = 0; i < array.length; i++) {
            if (Validation.isNotPrime(array[i])) {
                hasNotPrime = true;
                break;
            }
        }
        return hasNotPrime;
    }
}


