import com.stack.*;

import org.junit.Assert;
import org.junit.Test;
import java.util.EmptyStackException;

public class StackTest {

    @Test
    public void exampleTest() {
        Stack<Integer> stack = new Stack<>();

        stack.push(2);
        stack.push(7);
        stack.pop();
        stack.count();

        int variable = stack.pop();
        Assert.assertEquals(2, variable);
    }

    @Test
    public void emptyTest() {
        Stack<String> stack = new Stack<>();
        try {
            stack.pop();
            Assert.assertEquals(0, 1);
        } catch (EmptyStackException exception) {

        }
    }

    @Test
    public void littleIntTest() {
        Stack<Integer> stack = new Stack<>();
        int testsCount = 100;

        for (int i = 0; i < testsCount; i++) {
            Assert.assertEquals(i, stack.count());
            stack.push(i);
        }
        for (int i = 0; i < testsCount + i; i++, testsCount--) {
            Assert.assertEquals(testsCount, stack.count());
            int top = stack.pop();
            Assert.assertEquals(testsCount - 1, top);
        }

        try {
            stack.pop();
            Assert.assertEquals(0, 1);
        } catch (EmptyStackException exception) {
            return;
        }
    }

    @Test
    public void littleStringTest() {
        Stack<String> stack = new Stack<>();

        stack.push("Some");
        stack.push("text");
        stack.push("is here");

        Assert.assertEquals(3, stack.count());

        Assert.assertEquals(stack.pop(), "is here");
        Assert.assertEquals(stack.pop(), "text");
        Assert.assertEquals(stack.pop(), "Some");
    }

    @Test
    public void tryToMakeNegativeTest() {
        try {
            Stack<Float> stack = new Stack<>(-120);
            Assert.assertEquals(0, 5);
        } catch (IllegalArgumentException exception) {
            return;
        }
    }

    @Test
    public void iteratorTest() {
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i <= 30; i++) {
            s.push(i);
        }
        int check = 30;

        for (int i : s) {
            Assert.assertEquals(check, i);
            check--;
        }
    }
}