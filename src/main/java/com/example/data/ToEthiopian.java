package com.example.data;

// import java.time.LocalDate;

public class ToEthiopian {

    private static final int ETHIOPIAN_EPOCH_JDN = 1723856; // JDN of 1 Meskerem 1
    // 2460793
    // Convert a Gregorian date to Julian Day Number (JDN)
    private static int toJulianDayNumber(int year, int month, int day) {
        System.out.println("Input Gregorian date: " + day + "/" + month + "/" + year);

        int a = (14 - month) / 12;
        // System.out.println("Step 1 - a (adjust month): " + a);

        int y = year + 4800 - a;
        // System.out.println("Step 2 - y (adjusted year): " + y);

        int m = month + 12 * a - 3;
        // System.out.println("Step 3 - m (adjusted month): " + m);

        int jdn = day + (153 * m + 2) / 5
                    + 365 * y
                    + (y / 4)
                    - (y / 100)
                    + (y / 400)
                    - 32045;
        // System.out.println("Step 4 - Julian Day Number (JDN): " + jdn);

        return jdn;
    }

    // Convert a Gregorian date to Ethiopian date
    public static EthiopianDate getEthiopianDate(int gYear, int gMonth, int gDay) {
        int todayJDN = toJulianDayNumber(gYear, gMonth, gDay);

        int daysSinceEpoch = todayJDN - ETHIOPIAN_EPOCH_JDN;
        // System.out.println("Days since Ethiopian Epoch: " + daysSinceEpoch);

        int fourYearCycles = daysSinceEpoch / 1461; // 1 cycle = 4 years = 1461 days
        // System.out.println("Complete 4-year cycles: " + fourYearCycles);

        int daysLeft = daysSinceEpoch % 1461;
        // System.out.println("Remaining days after full 4-year cycles: " + daysLeft);

        int ethYear = 4 * fourYearCycles + daysLeft / 365;
        // System.out.println("Ethiopian year: " + ethYear);

        int dayOfYear = daysLeft % 365;
        // System.out.println("Day number in Ethiopian year: " + dayOfYear);

        int ethMonth = dayOfYear / 30 + 1;
        int ethDay = dayOfYear % 30 + 1;
        // System.out.println("Ethiopian month: " + ethMonth);
        // System.out.println("Ethiopian day: " + ethDay);

        return new EthiopianDate(ethYear, ethMonth, ethDay);
    }

    // Helper class to store Ethiopian date
    public static class EthiopianDate {
        private final int year;
        private final int month;
        private final int day;

        public EthiopianDate(int year, int month, int day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public int getYear() { return year; }
        public int getMonth() { return month; }
        public int getDay() { return day; }

        @Override
        public String toString() {
            return day + "/" + month + "/" + year;
        }
    }

    // Example usage
    // public static void main(String[] args) {
    //     LocalDate today = LocalDate.now();
    //     EthiopianDate ethDate = getEthiopianDate(today.getYear(), today.getMonthValue(), today.getDayOfMonth());

    //     // System.out.println("\n✅ Final Ethiopian date: " + ethDate);
    // }
}
