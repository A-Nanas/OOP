package com.oop.task_2_1_2;

import java.util.NoSuchElementException;

/**
 * Concurrent delivers who take pizza from warehouse and deliver it to destination
 * go home when no orders are expected to come and all pizza is delivered
 */

public final class DeliveryMan implements Runnable {
    private final int id;
    private final int deliverySpeed;
    Thread thread;
    Pizzeria employment;

    DeliveryMan(final int idGot, final int deliverySpeedGot, final Pizzeria employmentGot) {
        thread = new Thread(this, "Deliver");
        this.id = idGot;
        this.deliverySpeed = deliverySpeedGot;
        this.employment = employmentGot;

        System.out.println("Delivery man " + idGot + " is here");
        thread.start();
    }

    /**
     * takes pizza from warehouse if it is there and delivers it deliverySpeed time
     * says to pizzeria that one pizza was delivered
     * if pizza was being delivered too late, it is free
     * @param pizza - pizza to be delivered
     * @throws InterruptedException if interrupted
     */
    void deliver(final Pizza pizza) throws InterruptedException {
        pizza.statuses = Statuses.BEING_DELIVERED;

        pizza.printStatus();
        Thread.sleep(deliverySpeed);
        pizza.statuses = Statuses.DELIVERED;
        long curTime = System.currentTimeMillis();
        if (curTime - pizza.timeWasOrdered > employment.timeWhenPizzaBecomesFree) {
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
        while (employment.ordersStillCome || employment.pizzasInWork != 0) {
            try {
                piz = employment.warehouse.take();
                if (piz != null) {
                    this.deliver(piz);
                }
            } catch (InterruptedException e) {
                return;
            }
        }
        System.out.println("Deliveryman " + id + " ends work");
    }
}
