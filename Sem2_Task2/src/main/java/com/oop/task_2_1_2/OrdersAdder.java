package com.oop.task_2_1_2;

/**
 * Parallel putting pizza requests into order
 */

public final class OrdersAdder implements Runnable {
    private int id = 1;
    int amountOrders;
    long timeBetweenOrders;
    Thread thread;
    Pizzeria pizzeriaName;

    /**
     * @param amountOrdersGot amount of pizza requests to be put into Order
     * @param pizzeriaNameGot pizzeria name
     * @param timeBetweenOrdersGot frequency in which requests come
     */

    OrdersAdder(final int amountOrdersGot, final Pizzeria pizzeriaNameGot, final long timeBetweenOrdersGot){
        thread = new Thread(this, "Order Putter");
        this.amountOrders = amountOrdersGot;
        this.pizzeriaName = pizzeriaNameGot;
        this.timeBetweenOrders = timeBetweenOrdersGot;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            return;
        }
        thread.start();
    }

    @Override
    public void run() {
        while (id <= amountOrders) {
            Pizza piz = new Pizza(id);
            pizzeriaName.orderOfRequests.add(piz);
            pizzeriaName.pizzasInWork++;
            id++;
            try {
                Thread.sleep(timeBetweenOrders);
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println("all offers were put");
        pizzeriaName.ordersStillCome = false;
    }
}
