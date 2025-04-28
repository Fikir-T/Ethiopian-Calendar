package com.example.data;
public class EthiopianCalendar {

    private static final String[] WEEKDAYS = {
        "ሰኞ", "ማክሰኞ", "ረቡዕ", "ሓሙስ", "አርብ", "ቅዳሜ", "እሁድ"
    };

    // Julian Day Number from Ethiopian date
    public static int toJulianDayNumber(int year, int month, int day) {
        return  1723856 + 365 * year + (year / 4) + 30 * month + day - 31;
    }
    // Get the weekday from Ethiopian date
    public static String getWeekday(int year, int month, int day) {
        int jdn = toJulianDayNumber(year, month, day);
        int weekdayIndex = ((jdn % 7) + 7) % 7;  // fix to prevent negative index
        return WEEKDAYS[weekdayIndex];
    }
    

    // Optional: Ethiopian leap year
    public static boolean isLeapYear(int year) {
        return (year + 1) % 4 == 0;
    }

    public static void main(String[] args) {
        int year = 1995;
        int month = 8;
        int day = 12;

        String weekday = getWeekday(year, month, day);
        System.out.println("Ethiopian date " + day + "/" + month + "/" + year + " is a " + weekday);
    }
}