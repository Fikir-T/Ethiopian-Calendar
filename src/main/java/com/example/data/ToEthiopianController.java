package com.example.data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ToEthiopianController {

    @GetMapping("/to-ethiopian")
    public String convertToEthiopian(
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day,
            Model model) {

        // If no date provided, use today
        LocalDate today = LocalDate.now();
        int gYear = (year != null) ? year : today.getYear();
        int gMonth = (month != null) ? month : today.getMonthValue();
        int gDay = (day != null) ? day : today.getDayOfMonth();

        ToEthiopian.EthiopianDate ethDate = ToEthiopian.getEthiopianDate(gYear, gMonth, gDay);
        String weekday = EthiopianCalendar.getWeekday(ethDate.getYear(), ethDate.getMonth(), ethDate.getDay());

        model.addAttribute("gYear", gYear);
        model.addAttribute("gMonth", gMonth);
        model.addAttribute("gDay", gDay);
        model.addAttribute("ethYear", ethDate.getYear());
        model.addAttribute("ethMonth", ethDate.getMonth());
        model.addAttribute("ethDay", ethDate.getDay());
        model.addAttribute("weekday", weekday);

        return "to-ethiopian"; // Refers to templates/to-ethiopian.html
    }
}
