package com.oop.task_2_1_2;

public class OrdersAdder implements Runnable{
    private int id = 1;
    int amountOrders;
    long timeBetweenOrders;
    Thread thread;
    Pizzeria pizzeriaName;

    OrdersAdder(int amountOrders, Pizzeria pizzeriaName, long timeBetweenOrders){
        thread = new Thread(this, "Order Putter");
        this.amountOrders = amountOrders;
        this.pizzeriaName = pizzeriaName;
        this.timeBetweenOrders = timeBetweenOrders;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            return;
        }
        thread.start();
    }

    @Override
    public void run() {
        while(id <= amountOrders) {
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
