package com.oop.task_2_1_2;

enum Statuses {
    ORDERED,
    BAKING,
    BAKED,
    IN_WARE_HOUSE,
    BEING_DELIVERED,
    DELIVERED
}

public class Pizza {
    final int id;
    long timeWasOrdered;
    Statuses statuses;

    Pizza(int id) {
        this.id = id;
        statuses = Statuses.ORDERED;
        timeWasOrdered = System.currentTimeMillis();
    }

    void printStatus(){
        System.out.println(id + " " + statuses);
    }

    void printLate(){
        System.out.println(id + " pizza delivered too late -> it's free!!!");
    }
}
