package com.oop.task3_1;

public class Date {

    private int day;
    private int month;
    private int year;

    public String[] Weekday = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        if (isDayIncorrect(day, month, year)) {
            throw new IllegalArgumentException("Day must exist");
        }
        this.day = day;
    }

    public void setMonth(int month) {
        if (isMonthIncorrect(month)) {
            throw new IllegalArgumentException("Month must be > 0 and < 13");
        }
        this.month = month;
    }

    public void setYear(int year) {
        if (isYearIncorrect(year)) {
            throw new IllegalArgumentException("Year must be >= 0 and <= 12000");
        }
        this.year = year;
    }

    public static int daysInMonth(int month, int year) {
        switch (month) {
            case(4):
            case(6):
            case(9):
            case(11):
                return 30;
            case(2):
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return 31;
        }

    }

    public static boolean isLeapYear (int year) {
        if (year % 4 != 0) {
            return false;
        } else if (year % 100 != 0) {
            return true;
        } else if (year % 400 == 0) {
            return true;
        } else {
            return false;
        }

    }

    public Date() {
        day = 1;
        month = 1;
        year = 1;
    }

    public Date (int day, int month, int year) {
        if (isYearIncorrect(year)) {
            throw new IllegalArgumentException("Year must be >= 0 and <= 12000");
        }
        this.year = year;
        if (isMonthIncorrect(month)) {
            throw new IllegalArgumentException("Month must be > 0 and < 13");
        }
        this.month = month;
        if (isDayIncorrect(day, month, year)) {
            throw new IllegalArgumentException("Day must exist");
        }
        this.day = day;

    }

    public void setDate(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }

    private boolean isYearIncorrect (int year) {
        return (year < 0) || (year > 12000);
    }

    private boolean isMonthIncorrect (int month) {
        return (month < 0) || (month > 12);
    }

    private boolean isDayIncorrect (int day, int month, int year) {
        return !(day > 0 && day <= daysInMonth(month, year));
    }

    private int weekdayNumber () {
        int d = this.day;
        int m = this.month;
        int y = this.year;

        int a = (14 - m) / 12;
        y -= a;
        m += 12 * a - 2;
        return ((d + 31 * m / 12 + y + y / 4 - y / 100 + y / 400) % 7);
    }

    public String dayOfWeek() {
        int numberOfDayInWeek = weekdayNumber();
        return Weekday[numberOfDayInWeek];
    }

}

