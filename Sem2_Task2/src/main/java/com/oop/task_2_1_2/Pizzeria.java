package com.oop.task_2_1_2;

/**
 * Pizzeria with workers, warehouse and order of requests.
 */
public final class Pizzeria {
    private final int warehouseCapacity;
    private final int amountBakers;
    private final int amountDelivers;
    private final int bakersQuality = 400;
    private final int deliversSpeed = 1000;
    int timeWhenPizzaBecomesFree = 1600;
    int pizzasInWork;
    long timeOfBeginning;
    boolean ordersStillCome;
    private final int ordersAmount;
    int deliveredTooLate;
    private final long timeBetwOrders;

    Warehouse warehouse;
    Order orderOfRequests;
    OrdersAdder ordersAdder;
    DeliveryMan[] deliveryMen;
    Baker[] bakers;

    /**
     * Too many params, they could be passed by an object.
     * Creates ordersAdder, bakers and deliverymen threads;
     * creates warehouse and order
     * puts links of deliveryman's threads to array
     * @param warehouseCapacityGot capacity of warehouse
     * @param amountBakersGot amount of bakers
     * @param amountDeliversGot amount of delivers
     * @param ordersAmountGot amount of pizza requests
     * @param timeBetwOrdersGot time between pizza requests
     */
    Pizzeria(final int warehouseCapacityGot, final int amountBakersGot, final int amountDeliversGot,
             final int ordersAmountGot, final long timeBetwOrdersGot) {
        this.warehouseCapacity = warehouseCapacityGot;
        this.amountBakers = amountBakersGot;
        this.amountDelivers = amountDeliversGot;
        this.ordersAmount = ordersAmountGot;
        this.timeBetwOrders = timeBetwOrdersGot;
        pizzasInWork = 0;
        timeOfBeginning = System.currentTimeMillis();
        ordersStillCome = true;
        deliveredTooLate = 0;

        warehouse = new Warehouse(warehouseCapacityGot);
        orderOfRequests = new Order();
        deliveryMen = new DeliveryMan[amountDeliversGot];
        bakers = new Baker[amountBakersGot];
        ordersAdder = new OrdersAdder(ordersAmountGot, this, timeBetwOrdersGot);

        for (int i = 1; i <= amountBakersGot; i++) {
            Baker baker = new Baker(i, bakersQuality, this);
            bakers[i - 1] = baker;
        }

        for (int i = 1; i <= amountDeliversGot; i++) {
            DeliveryMan deliver = new DeliveryMan(i, deliversSpeed, this);
            deliveryMen[i - 1] = deliver;
        }

        for (int i = 0; i < amountBakers; i++) {
            try {
                this.bakers[i].thread.join();
            } catch (InterruptedException e) {
                return;
            }
        }

        for (int i = 0; i < amountDelivers; i++) {
            try {
                this.deliveryMen[i].thread.join();
            } catch (InterruptedException e) {
                return;
            }
        }

        try {
            this.ordersAdder.thread.join();
        } catch (InterruptedException e) {
            return;
        }
    }

    /**
     * @return Outputs object with results:
     * add, delete or do nothing with bakers or delivers;
     * make warehouse bigger or not
     */
    Outputs results() {
        Outputs res = new Outputs();
        System.out.println(deliveredTooLate + " pizzas were delivered too long");
        if (deliveredTooLate > ordersAmount / 10) {
            if (warehouse.timesWarehouseIsFull > 0 && (warehouse.timesWarehouseIsFull * 10) >= deliveredTooLate) {
                res.recommendsForStaff = Recommendations.ADD_DELIVERS;
            } else {
                res.recommendsForStaff = Recommendations.ADD_BAKERS;
            }
        } else {
            res.recommendsForStaff = Recommendations.STAFF_IS_OK;
            if ((long) this.amountBakers / (long) this.bakersQuality
                    - (long) this.amountDelivers / (long) this.deliversSpeed > 1) {
                res.recommendsForStaff = Recommendations.DELETE_BAKERS;
            } else {
                if ((long) this.amountBakers / (long) this.bakersQuality
                        - (long) this.amountDelivers / (long) this.deliversSpeed < -1) {
                    res.recommendsForStaff = Recommendations.DELETE_DELIVERS;
                }
            }
            if ((float) this.bakersQuality / this.amountBakers + (float) this.deliversSpeed / this.amountDelivers
                    < this.timeBetwOrders
                    + (float) this.timeWhenPizzaBecomesFree / (amountBakers + amountDelivers + 2)) {
                float timing = (((float) this.amountBakers / (float) this.bakersQuality)
                        - ((float) this.amountDelivers / (float) this.deliversSpeed));
                System.out.println(timing);
                if (timing > 0) {
                    res.recommendsForStaff = Recommendations.DELETE_BAKERS;
                } else {
                    res.recommendsForStaff = Recommendations.DELETE_DELIVERS;
                }
            }
        }
        res.recommendsForWarehouse = Recommendations.WAREHOUSE_IS_OK;
        if (deliveredTooLate != 0 && warehouseCapacity < amountBakers * 1.3) {
            res.recommendsForWarehouse = Recommendations.INCREASE_WAREHOUSE;
        }
        return res;
    }
}
