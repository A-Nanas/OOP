package com.stack;

import java.lang.IllegalArgumentException;
import java.util.*;

public class Stack <T> implements Iterable<T>{

    private int top = 0;
    private Object[] storage;
    private final static int SIZE = 12;

    public Stack(int capacity) throws IllegalArgumentException {
        if (capacity < 0) {
            throw new IllegalArgumentException("Stack capacity should not be a negative number");
        }
        storage = new Object[capacity];
    }

    public Stack() {
        storage = new Object[SIZE];
    }

    public StackIterator iterator() {
        return new StackIterator();
    }

    public void push(T inputValue) {
        if (top >= this.storage.length) {
            growStorage();
        }
        storage[top] = inputValue;
        top++;
    }

    public T pop() throws EmptyStackException {
        if (top == 0) {
            throw new EmptyStackException();
        }
        top--;
        T result = (T)storage[top];
        if (top <= storage.length / 4) {
            shrinkStorage();

        }
        return result;
    }

    public int count() {
        return top;
    }

    private void growStorage() {
        storage = Arrays.copyOf(storage, storage.length * 2 + 1);
    }

    private void shrinkStorage() {
        storage = Arrays.copyOf(storage, storage.length / 2);
    }

    public class StackIterator implements Iterator {
        final private Stack stack = new Stack();
        private int currentIdx;

        public StackIterator() {
            currentIdx = stack.count() - 1;
        }

        public boolean hasNext() {
            return currentIdx >= 0;
        }

        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            currentIdx--;
            return stack.storage[currentIdx + 1];
        }
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}