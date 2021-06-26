package com.oop.task_2_1_2;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

public class Order {
    ArrayBlockingQueue<Pizza> order;

    Order () {
        int capacity = 10000;
        order = new ArrayBlockingQueue<>(capacity, true);
    }

    public void add(Pizza pizza) {
        pizza.printStatus();
        order.offer(pizza);
    }

    public boolean isEmpty(){
        return order.isEmpty();
    }

    public Pizza take() throws InterruptedException, NoSuchElementException {
        Pizza pizza = order.poll();
        return pizza;
    }
}
