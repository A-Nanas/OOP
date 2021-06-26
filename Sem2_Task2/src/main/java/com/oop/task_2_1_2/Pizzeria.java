package com.oop.task_2_1_2;

public class Pizzeria {
    private int warehouseCapacity;
    private int amountBakers;
    private int amountDelivers;
    private final int bakersQuality = 400;
    private final int deliversSpeed = 1000;
    int timeWhenPizzaBecomesFree = 1600;
    int pizzasInWork;
    long timeOfBeginning;
    boolean ordersStillCome;
    private int ordersAmount;
    int deliveredTooLate;
    private long timeBetwOrders;

    Warehouse warehouse;
    Order orderOfRequests;
    DeliveryMan[] deliveryMen;

    Pizzeria (int warehouseCapacity, int amountBakers, int amountDelivers, int ordersAmount, long timeBetwOrders) {
        this.warehouseCapacity = warehouseCapacity;
        this.amountBakers = amountBakers;
        this.amountDelivers = amountDelivers;
        this.ordersAmount = ordersAmount;
        this.timeBetwOrders = timeBetwOrders;
        pizzasInWork = 0;
        timeOfBeginning = System.currentTimeMillis();
        ordersStillCome = true;
        deliveredTooLate = 0;

        warehouse = new Warehouse(warehouseCapacity);
        orderOfRequests = new Order();
        deliveryMen = new DeliveryMan[amountDelivers];
        new OrdersAdder(ordersAmount, this, timeBetwOrders);

        for(int i = 1; i <= amountBakers; i++) {
            new Baker(i, bakersQuality, this);
        }

        for(int i = 1; i <= amountDelivers; i++) {
            DeliveryMan deliver = new DeliveryMan(i, deliversSpeed, this);
            deliveryMen[i - 1] = deliver;
        }
    }

    Outputs results() {
        Outputs res = new Outputs();
        System.out.println(deliveredTooLate + " pizzas were delivered too long");
        if(deliveredTooLate > ordersAmount / 10) {
            if (warehouse.timesWarehouseIsFull > 0 && (warehouse.timesWarehouseIsFull * 10) >= deliveredTooLate) {
                res.recommendsForStaff = Recommendations.ADD_DELIVERS;
            }else {
                res.recommendsForStaff = Recommendations.ADD_BAKERS;
            }
        } else {
            res.recommendsForStaff = Recommendations.STAFF_IS_OK;
            if ((long) this.amountBakers / (long) this.bakersQuality -
                    (long) this.amountDelivers / (long) this.deliversSpeed > 1) {
                res.recommendsForStaff = Recommendations.DELETE_BAKERS;
            } else {
                if ((long) this.amountBakers / (long) this.bakersQuality -
                        (long) this.amountDelivers / (long) this.deliversSpeed < -1) {
                    res.recommendsForStaff = Recommendations.DELETE_DELIVERS;
                }
            }
            if((float) this.bakersQuality / this.amountBakers + (float) this.deliversSpeed / this.amountDelivers <
                    this.timeBetwOrders + (float) this.timeWhenPizzaBecomesFree / (amountBakers + amountDelivers + 2)) {
                float timing = (((float) this.amountBakers / (float) this.bakersQuality) -
                        ((float) this.amountDelivers / (float) this.deliversSpeed));
                System.out.println(timing);
                if (timing > 0) {
                    res.recommendsForStaff = Recommendations.DELETE_BAKERS;
                } else {
                    res.recommendsForStaff = Recommendations.DELETE_DELIVERS;
                }
            }
        }
        res.recommendsForWarehouse = Recommendations.WAREHOUSE_IS_OK;
        if(deliveredTooLate != 0 && warehouseCapacity < amountBakers * 1.3){
            res.recommendsForWarehouse = Recommendations.INCREASE_WAREHOUSE;
        }
        return res;
    }
}
