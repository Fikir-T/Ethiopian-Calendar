package com.example.data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.time.LocalDate;
@Controller
public class MiyaziaTest {

    @GetMapping("/miyazia")
    public String miyazia(@RequestParam(required = false) Integer year,
                          @RequestParam(required = false) Integer month,
                          Model model) {     
        ArrayList<String> weekdays = new ArrayList<>();
        ArrayList<Integer> days = new ArrayList<>();
        // int year = 2023;
        // int month = 13;
        LocalDate today = LocalDate.now();
        ToEthiopian.EthiopianDate etdate = ToEthiopian.getEthiopianDate(today.getYear(), today.getMonthValue(), today.getDayOfMonth());
        if (year == null ){
            year = etdate.getYear();
        }
        if (month == null ){
            month = etdate.getMonth(); // Assuming getMonth() is the correct method
        }        
        int ken;
        boolean isLeap;
        if ((year + 1) % 4 == 0) {
            isLeap = true;
        } else {
            isLeap = false;
        }
        if (month <= 12 ){
            ken =30;
        }
        else if (month == 13 && isLeap ){
            ken = 6;
        }
        else if ( month == 13 && !isLeap){
            ken=5;
        }
        else{
            ken = 0;
        }
        for ( int day = 1; day  <= ken; day++) {
            String weekday = EthiopianCalendar.getWeekday(year, month, day);
            weekdays.add(weekday);
            days.add(day);
        }
        model.addAttribute("days", days);
        model.addAttribute("weekdays", weekdays);
        return "miyazia"; 
    }
}