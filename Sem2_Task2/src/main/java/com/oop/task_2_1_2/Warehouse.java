package com.oop.task_2_1_2;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * contains Pizza not more than capacity
 */

public final class Warehouse {
    private final ArrayBlockingQueue<Pizza> warehouse;
    int timesWarehouseIsFull;

    Warehouse(final int capacity) {
        warehouse = new ArrayBlockingQueue<>(capacity, true);
        timesWarehouseIsFull = 0;
    }

    /**
     * counts if warehouse is full
     * @param pizza to be added to warehouse
     * @throws InterruptedException if is interrupted
     */
    public void add(final Pizza pizza) throws InterruptedException {
        warehouse.put(pizza);
        if (pizza != null && warehouse.remainingCapacity() == 0) {
            timesWarehouseIsFull++;
        }
    }

    /**
     *
     * @return pizza if warehouse is not empty, else returns null
     */
    public Pizza take() {
        return warehouse.poll();
    }
}
