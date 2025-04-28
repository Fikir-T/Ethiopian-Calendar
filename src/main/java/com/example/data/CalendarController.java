package com.example.data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class CalendarController {
    @GetMapping("/ethiopian")
    public String showForm() {
        return "ethiopian_form";
    }
    @PostMapping("/ethiopian")
    public String calculateWeekday(@RequestParam int year,
                                   @RequestParam int month,
                                   @RequestParam int day,
                                   Model model) {

        String weekday = EthiopianCalendar.getWeekday(year, month, day);
        model.addAttribute("weekday", weekday);
        model.addAttribute("date", day + "/" + month + "/" + year);
        return "ethiopian_result";
    }
}