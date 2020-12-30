package com.oop.task_3_3;


import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class OrderedSetTest {

    @Test
    public void repeatedAliasExceptionTest() {
        try {
            new OrderedSet<>(new Integer[]{1, 1, 2});
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Exception was not caught");
    }

    @Test
    public void maxElementTest() {
        OrderedSet<String> orderedSet = new OrderedSet<>(new String[] {"one", "two", "three", "four", "five", "six"});

        orderedSet.addNewRelation("two", "one");
        orderedSet.addNewRelation("three", "two");
        orderedSet.addNewRelation("six", "five");
        orderedSet.addNewRelation("five", "four");

        List<String> expectedAnswer = new ArrayList<>();
        expectedAnswer.add("three");
        expectedAnswer.add("six");
        assertEquals(expectedAnswer, orderedSet.findMaxElements());
    }

    @Test
    public void topsortTest() {
        OrderedSet<Integer> orderedSet = new OrderedSet<>(new Integer[] {1, 2, 3, 4, 5, 6, 7});

        orderedSet.addNewRelation(1, 2);
        orderedSet.addNewRelation(2, 3);
        orderedSet.addNewRelation(3, 7);
        orderedSet.addNewRelation(4, 5);
        orderedSet.addNewRelation(5, 3);
        orderedSet.addNewRelation(6, 7);
        Integer[] answer = new Integer[]{6, 4, 5, 1, 2, 3, 7};
        assertArrayEquals(answer, orderedSet.getTopologicalSorting());
    }


    @Test
    public void transitivityExceptionTest() {
        OrderedSet<String> set = new OrderedSet<>(new String[] {"ein", "not ein"});

        set.addNewRelation("ein", "not ein");
        try {
            set.addNewRelation("not ein", "ein");
        } catch (IllegalStateException e) {
            return;
        }
        fail("No exception");
    }
}