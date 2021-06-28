package com.oop.task_2_1_2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Contains pizzas requested
 */
public final class Order {
    ArrayBlockingQueue<Pizza> order;

    Order() {
        int capacity = 10000;
        order = new ArrayBlockingQueue<>(capacity, true);
    }

    /**
     * @param pizza is added to order
     */
    public void add(final Pizza pizza) {
        pizza.printStatus();
        order.offer(pizza);
    }

    /**
     * @return true is order is empty, else false
     */
    public boolean isEmpty() {
        return order.isEmpty();
    }

    /**
     * @return pizza from order
     * @throws InterruptedException if interrupted
     */
    public Pizza take() throws InterruptedException {
        return order.poll();
    }
}
