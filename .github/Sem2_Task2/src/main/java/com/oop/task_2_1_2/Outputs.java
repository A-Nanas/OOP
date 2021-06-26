package com.oop.task_2_1_2;

enum Recommendations{
    STAFF_IS_OK,
    ADD_DELIVERS,
    ADD_BAKERS,
    DELETE_BAKERS,
    DELETE_DELIVERS,
    INCREASE_WAREHOUSE,
    WAREHOUSE_IS_OK
}

public class Outputs {
    Recommendations recommendsForStaff;
    Recommendations recommendsForWarehouse;
}
