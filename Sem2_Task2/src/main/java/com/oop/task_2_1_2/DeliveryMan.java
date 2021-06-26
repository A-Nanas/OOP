package com.oop.task_2_1_2;

import java.util.NoSuchElementException;

public class DeliveryMan implements Runnable{
    private final int id;
    private final int deliverySpeed;
    Thread thread;
    Pizzeria employment;

    DeliveryMan (int id, int deliverySpeed, Pizzeria employment) {
        thread = new Thread(this, "Deliver");
        this.id = id;
        this.deliverySpeed = deliverySpeed;
        this.employment = employment;

        System.out.println("Delivery man " + id + " is here");
        thread.start();
    }

    void deliver(Pizza pizza) throws InterruptedException {
        pizza.statuses = Statuses.BEING_DELIVERED;

        pizza.printStatus();
        Thread.sleep(deliverySpeed);
        pizza.statuses = Statuses.DELIVERED;
        long curTime = System.currentTimeMillis();
        if(curTime - pizza.timeWasOrdered > employment.timeWhenPizzaBecomesFree){
            pizza.printLate();
            employment.deliveredTooLate++;
        } else {
            pizza.printStatus();
        }
        employment.pizzasInWork--;
    }

    @Override
    public void run() {
        Pizza piz;
        while(employment.ordersStillCome || employment.pizzasInWork != 0) {
            try {
                try {
                    piz = employment.warehouse.take();
                    if(piz != null) {
                        this.deliver(piz);
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }catch (NoSuchElementException e){
                return;
            }
        }
        System.out.println("Deliveryman " + id + " ends work");
    }
}
