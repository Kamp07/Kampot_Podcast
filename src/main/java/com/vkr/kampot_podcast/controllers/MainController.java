package com.vkr.kampot_podcast.controllers;

import com.vkr.kampot_podcast.repository.ReviewRepository;
import com.vkr.kampot_podcast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController
{
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home(Model model)
    {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model)
    {
        model.addAttribute("title", "Страница про нас");
        return "about";
    }

    @GetMapping("/radio")
    public String radio() {
        return "radio";
    }





}
