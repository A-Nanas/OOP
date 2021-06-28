package com.oop.task_2_1_2;

import java.util.NoSuchElementException;

/**
 * Concurrent bakers take pizza to bake if ordered.
 * and put it to warehouse
 * go home when no orders are expected to come and all orders are already baked
 */

public final class Baker implements Runnable {
    final int id;
    private final int bakerQuality;
    Thread thread;
    Pizzeria employment;

    Baker(final int idGot, final int bakerQualityGot, final Pizzeria employmentGot) {
        thread = new Thread(this, "Baker");
        this.id = idGot;
        this.bakerQuality = bakerQualityGot;
        this.employment = employmentGot;

        System.out.println("Baker number " + idGot + " is ready to bake");
        thread.start();
    }

    /**
     * bakes pizza for bakerQuality of time.
     * @param pizza - pizza to be baked
     * @throws InterruptedException if interrupted
     */

    void bake(final Pizza pizza) throws InterruptedException {
        pizza.statuses = Statuses.BAKING;
        pizza.printStatus();

        Thread.sleep(bakerQuality);
        pizza.statuses = Statuses.BAKED;
        pizza.printStatus();
    }

    /**
     * puts pizza to warehouse; if it is full waits for place.
     * @param pizza - pizza to be put
     * @throws InterruptedException
     */

    void putToWarehouse(final Pizza pizza) throws InterruptedException {
        pizza.statuses = Statuses.IN_WARE_HOUSE;
        pizza.printStatus();
        employment.warehouse.add(pizza);
    }

    @Override
    public void run() {
        while (employment.ordersStillCome || (!employment.orderOfRequests.isEmpty())) {
            try {
                try {
                    Pizza piz;
                    piz = employment.orderOfRequests.take();
                    if (piz != null) {
                        this.bake(piz);
                        this.putToWarehouse(piz);
                    }
                } catch (InterruptedException f) {
                    return;
                }
            } catch (NoSuchElementException e) {
                return;
            }
        }
        System.out.println("Baker " + id + " goes home");
    }
}
