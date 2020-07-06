package com.fatec.scel2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "menu";
    }

    @GetMapping("/menu")
    public String menu() {
        return "menu";
    }
}