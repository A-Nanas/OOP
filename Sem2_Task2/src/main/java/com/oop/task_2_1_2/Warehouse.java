package com.oop.task_2_1_2;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

public class Warehouse {
    ArrayBlockingQueue<Pizza> warehouse;
    int timesWarehouseIsFull;

    Warehouse (int capacity) {
        warehouse = new ArrayBlockingQueue<>(capacity, true);
        timesWarehouseIsFull = 0;
    }

    public void add(Pizza pizza) throws InterruptedException {
        warehouse.put(pizza);
        if(pizza != null && warehouse.remainingCapacity() == 0){
            timesWarehouseIsFull++;
        }
    }

    public synchronized Pizza take() throws NoSuchElementException {
        return warehouse.poll();
    }
}
