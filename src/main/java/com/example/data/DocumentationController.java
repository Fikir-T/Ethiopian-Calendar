package com.example.data;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DocumentationController {
    @GetMapping("/documentation")
    public String documentation() {
        return "documentation"; 
    }

    
}
