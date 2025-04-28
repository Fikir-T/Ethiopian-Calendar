package com.example.data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TableController {

    @GetMapping("/calendar")
    public String calendarView(@RequestParam(defaultValue = "2017") int year,
                               @RequestParam(defaultValue = "1") int month,
                               Model model) {

        List<DayInfo> calendarDays = new ArrayList<>();
        int daysInMonth = 30;

        if (month == 13) {
            if (EthiopianCalendar.isLeapYear(year)) {
                daysInMonth = 6;
            } else {
                daysInMonth = 5;
            }
        }

        // Determine starting weekday
        String firstDayName = EthiopianCalendar.getWeekday(year, month, 1);
        int weekdayOffset = switch (firstDayName) {
            case "ሰኞ" -> 1;
            case "ማክሰኞ" -> 2;
            case "ረቡዕ" -> 3;
            case "ሓሙስ" -> 4;
            case "አርብ" -> 5;
            case "ቅዳሜ" -> 6;
            case "እሁድ" -> 0;
            default -> 0;
        };

        // Pad empty cells before the first day
        for (int i = 0; i < weekdayOffset; i++) {
            calendarDays.add(new DayInfo("", ""));
        }

        // Fill actual calendar days
        for (int day = 1; day <= daysInMonth; day++) {
            String weekday = EthiopianCalendar.getWeekday(year, month, day);
            calendarDays.add(new DayInfo(day + "/" + month + "/" + year, weekday));
        }

        model.addAttribute("calendarDays", calendarDays);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedMonth", month);
        return "calendar";
    }

    public static class DayInfo {
        private String date;
        private String weekday;

        public DayInfo(String date, String weekday) {
            this.date = date;
            this.weekday = weekday;
        }

        public String getDate() {
            return date;
        }

        public String getWeekday() {
            return weekday;
        }
    }
}
