package com.blossom.forth.progress.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

    @GetMapping("/results")
    public String results(Model model) {

        return "results";
    }

    @GetMapping("/create_split")
    public String splitCreation(Model model) {

        return "split-add";
    }

    @GetMapping("/splits")
    public String splits(Model model) {
        return "splits";
    }

}
