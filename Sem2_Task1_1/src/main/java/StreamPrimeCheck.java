import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPrimeCheck {
    //Our prime numbers "flag".
    static boolean hasNotPrime = false;

    public static boolean streamRun(Long[] array) {
        List<Long> list = Arrays.asList(array);

        Optional<Long> n = list
                .parallelStream()
                .filter(Validation::isNotPrime)
                .findFirst();

        if (n.isPresent()) {
            hasNotPrime = true;
        }
        return hasNotPrime;
    }
}
