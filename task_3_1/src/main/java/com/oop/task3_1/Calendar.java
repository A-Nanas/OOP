package com.oop.task3_1;
import com.oop.task3_1.Date.*;

public class Calendar {

    public Date addDays(int amount, Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        int daysOnMonth = Date.daysInMonth(month, year);

        if (amount <= daysOnMonth - day) {
            day += amount;
            amount = 0;
        } else {
            amount -= daysOnMonth - day + 1;
            day = 1;
            month++;
            if (month > 12) {
                year++;
                month -= 12;
            }
        }
        daysOnMonth = Date.daysInMonth(month, year);

        while (amount != 0) {
            if (amount >= daysOnMonth) {
                month++;
                amount -= daysOnMonth;
            } else {
                day += amount;
                break;
            }
            if (month > 12) {
                year++;
                month -= 12;
            }
            daysOnMonth = Date.daysInMonth(month, year);
        }
        return new Date(day, month, year);

    }

    public Date addMonths(int amount, Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        month += amount;

        if (month > 12) {
            year = year + month / 12;
            month = month % 12;
            int numberOfDays = Date.daysInMonth(month, year);
            if (day > numberOfDays) {
                day = day - numberOfDays;
                month++;
            }
        }
        return new Date(day, month, year);

    }

    public Date addYears(int amount, Date date) {
        return new Date(date.getDay(), date.getMonth(), date.getYear() + amount);
    }

    public Date subDays(int amount, Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        int numberOfDays;
        if (amount < day) {
            day -= amount;
            amount = 0;
        } else {
            amount -= day;
            month--;
            if (month < 1) {
                year--;
                month += 12;
            }
            numberOfDays = Date.daysInMonth(month, year);
            day = numberOfDays;

        }
        numberOfDays = Date.daysInMonth(month, year);
        while (amount != 0) {
            if (amount > numberOfDays) {
                month--;
                amount -= numberOfDays;
            } else {
                day = numberOfDays - amount;
                break;
            }
            if (month < 1) {
                year--;
                month += 12;
            }
            numberOfDays = Date.daysInMonth(month, year);
        }
        return new Date(day, month, year);
    }

    public Date subMonths(int amount, Date date) {
        int day = date.getDay();
        int month = date.getMonth();
        int year = date.getYear();
        if (month - amount < 1) {
//            month += amount;
//            year -= month / 12;
//            month = 12 - month % 12;
            amount -= month;
            year -= 1 + amount / 12;
            month = 12 - amount % 12;
        } else {
            month -= amount;
        }
        int numberOfDays = Date.daysInMonth(month, year);
        if (numberOfDays < day) {
            day -= numberOfDays;
            month++;
        }
        return new Date(day, month, year);
    }

    public Date subYears(int amount, Date date) {
        return new Date(date.getDay(), date.getMonth(), date.getYear() - amount);
    }

    public Date subDates(Date minuend, Date subtrahend) {
        int year = 0;
        int month = 0;
        int day = 0;
        while (subtrahend.getYear() != minuend.getYear()) {
            subtrahend = addYears(1, subtrahend);
            year++;
        }
        if (subtrahend.getMonth() > minuend.getMonth()) {
            while (subtrahend.getMonth() != minuend.getMonth()) {
                subtrahend = subMonths(1, subtrahend);
                month--;
            }
        } else {
            while (subtrahend.getMonth() != minuend.getMonth()) {
                subtrahend = addMonths(1, subtrahend);
                month++;
            }
        }
        if (month <= 0) {
            year--;
            month = 12 + month;
        }
        if(subtrahend.getDay() > minuend.getDay()) {
            while (subtrahend.getDay() != minuend.getDay()) {
                subtrahend = subDays(1, subtrahend);
                day--;
            }
        } else {
            while (subtrahend.getDay() != minuend.getDay()) {
                subtrahend = addDays(1, subtrahend);
                day++;
            }
        }
        if (day < 0 && -day > Date.daysInMonth(month, year)) {
            day = Date.daysInMonth(month, year) - day;
            month--;
            if(month < 0){
                year--;
                month = 12 - month;
            }
        } else {
            if (day < 0) {
                month--;
                day = Date.daysInMonth(month, year) + day;
                if (month < 0) {
                    year--;
                    month = 12 - month;
                }
            }
        }
        System.out.println(day + " days, " + month + " months, " + year + " years.");
        return new Date(day, month, year);
    }

}

