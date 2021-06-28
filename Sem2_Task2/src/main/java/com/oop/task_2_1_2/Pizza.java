package com.oop.task_2_1_2;

/**
 * statuses pizza can have
 */
enum Statuses {
    ORDERED,
    BAKING,
    BAKED,
    IN_WARE_HOUSE,
    BEING_DELIVERED,
    DELIVERED
}

public final class Pizza {
    final int id;
    long timeWasOrdered;
    Statuses statuses;

    Pizza(final int id) {
        this.id = id;
        statuses = Statuses.ORDERED;
        timeWasOrdered = System.currentTimeMillis();
    }

    /**
     * prints pizza id and status
     */
    void printStatus() {
        System.out.println(id + " " + statuses);
    }

    /**
     * says that pizza is free
     */
    void printLate() {
        System.out.println(id + " pizza delivered too late -> it's free!!!");
    }
}
