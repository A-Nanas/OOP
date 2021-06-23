package com.oop.task_2_1_1;

import java.util.Arrays;
import java.util.List;

public class StreamPrimeCheck {
    //Our prime numbers "flag".
    static boolean hasNotPrime = false;

    public static boolean streamRun(Long[] array) {
        List<Long> list = Arrays.asList(array);

        hasNotPrime = list
                .parallelStream()
                .anyMatch(Validation::isNotPrime);

        // hasNotPrime = list
        //         .parallelStream()
        //         .filter(Validation::isNotPrime)
        //         .isPresent();
        //
        // Optional<Long> n = list
        //         .parallelStream()
        //         .filter(Validation::isNotPrime)
        //         .findFirst();
        //
        // if (n.isPresent()) {
        //     hasNotPrime = true;
        // }
        return hasNotPrime;
    }
}
