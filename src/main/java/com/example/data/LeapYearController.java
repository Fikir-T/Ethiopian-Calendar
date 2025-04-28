package com.example.data;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LeapYearController {

    @GetMapping("/leap")
    public String showForm() {
        return "leap";
    }

    @PostMapping("/check")
    public String checkLeapYear(@RequestParam("year") int year, Model model) {
        boolean isLeap;
        if ((year + 1) % 4 == 0) {
            isLeap = true;
        } else {
            isLeap = false;
        }

        String result;
        if (isLeap) {
            result = year + " is a leap year in the Ethiopian calendar.";
        } else {
            result = year + " is not a leap year in the Ethiopian calendar.";
        }

        model.addAttribute("result", result);
        return "leap";

    }
}
