package com.oop.task_2_1_2;

import java.util.NoSuchElementException;

public class Baker implements Runnable{
    int id;
    private final int bakerQuality;
    Thread thread;
    Pizzeria employment;

    Baker(int id, int bakerQuality, Pizzeria employment) {
        thread = new Thread(this, "Baker");
        this.id = id;
        this.bakerQuality = bakerQuality;
        this.employment = employment;

        System.out.println("Baker number " + id + " is ready to bake");
        thread.start();
    }

    void bake(Pizza pizza) throws InterruptedException {
        pizza.statuses = Statuses.BAKING;
        pizza.printStatus();

        Thread.sleep(bakerQuality);
        pizza.statuses = Statuses.BAKED;
        pizza.printStatus();
    }
    
    void putToWarehouse(Pizza pizza) throws InterruptedException {
        pizza.statuses = Statuses.IN_WARE_HOUSE;
        pizza.printStatus();
        employment.warehouse.add(pizza);
    }

    @Override
    public void run() {
        while(employment.ordersStillCome || (!employment.orderOfRequests.isEmpty())) {
            try {
                try{
                    Pizza piz;
                    piz = employment.orderOfRequests.take();
                    if(piz != null) {
                        this.bake(piz);
                        this.putToWarehouse(piz);
                    }
                } catch (InterruptedException f){
                    return;
                }
            }catch (NoSuchElementException e){
                return;
            }
        }
        System.out.println("Baker " + id + " goes home");
    }
}
