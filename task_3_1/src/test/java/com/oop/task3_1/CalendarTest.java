package com.oop.task3_1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import com.oop.task3_1.Date;

class CalendarTest {

    @Test
    void addDaysTest() {
        Date date = new Date();

        Calendar calendar = new Calendar();
        date = calendar.addDays(59, date);
        Assertions.assertEquals(3, date.getMonth());
        Assertions.assertEquals(1, date.getYear());
        Assertions.assertEquals(1, date.getDay());
    }

    @Test
    void addMonthsTest() {
        Date date = new Date();
        Calendar calendar = new Calendar();
        for (int i = 0; i < 100; i++){
        date = calendar.addMonths(12, date);
        Assertions.assertEquals(i + 2, date.getYear());
        Assertions.assertEquals(1, date.getMonth());
        }
        date = calendar.addMonths(13, date);
        Assertions.assertEquals(102, date.getYear());
        Assertions.assertEquals(2, date.getMonth());
    }

    @Test
    void addYearsTest() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        for (int i = 0; i < 100; i++){
            Assertions.assertEquals(i + 2020, date.getYear());
            date = calendar.addYears(1, date);
        }
    }

    @Test
    void subDaysTest() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        date = calendar.subDays(31, date);
        Assertions.assertEquals(9, date.getDay());
        Assertions.assertEquals(11, date.getMonth());
        Assertions.assertEquals(2020, date.getYear());
    }

    @Test
    void subMonthsTest() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        date = calendar.subMonths(21, date);
        Assertions.assertEquals(3, date.getMonth());
        Assertions.assertEquals(2019, date.getYear());
        date = calendar.subMonths(24, date);
        Assertions.assertEquals(3, date.getMonth());
        Assertions.assertEquals(2017, date.getYear());
    }

    @Test
    void subYears() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        for( int i = 0; i < 20; i = i + 21) {
            Assertions.assertEquals(2020 - i * 21, date.getYear());
            date = calendar.subYears(21, date);
        }
    }

    @Test
    void subDates() {
        Date date = new Date(10, 12, 2020);
        Date date1 = new Date(1, 12, 2018);
        Calendar calendar = new Calendar();
        date = calendar.subDates(date, date1);
        Assertions.assertEquals(9, date.getDay());
        Assertions.assertEquals(12, date.getMonth());
        Assertions.assertEquals(1, date.getYear());
        date.setDate(1, 12, 2020);
        date1.setDate(10, 12, 2018);
        date = calendar.subDates(date, date1);
        Assertions.assertEquals(21, date.getDay());
        Assertions.assertEquals(11, date.getMonth());
        Assertions.assertEquals(1, date.getYear());
    }

    @Test
    void fromTask1() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        date = calendar.subDays(1024, date);
        System.out.println(date.dayOfWeek());
    }

    @Test
    void fromTask2() {
        Date date = new Date(10, 12, 2020);
        Date date1 = new Date(9, 5, 1945);
        Calendar calendar = new Calendar();
        date = calendar.subDates(date, date1);
    }

    @Test
    void fromTask3() {
        Date date = new Date(12, 1, 2002);
        System.out.println("In my birthday it was:");
        System.out.println(date.dayOfWeek());
    }

    @Test
    void fromTask4() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        date = calendar.addDays(17 * 7, date);
        System.out.println("month in 17 weeks:");
        System.out.println(date.getMonth());
    }

    @Test
    void fromTask5() {
        Date date = new Date(31, 12, 2022);
        Date date1 = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        date = calendar.subDates(date, date1);
        System.out.println("the new year will be in: ");
        System.out.println(date.getDay());
    }

    @Test
    void fromTask6() {
        Date date = new Date(10, 12, 2020);
        Calendar calendar = new Calendar();
        while (date.getDay() != 13) {
            date = calendar.addDays(7, date);
        }
        System.out.println("The nearest friday 13 is in: " + date.getDay() + "." + date.getMonth() + "." + date.getYear());
    }

}