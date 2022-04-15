package com.vkr.kampot_podcast.controllers;

import com.vkr.kampot_podcast.models.Reviews;
import com.vkr.kampot_podcast.models.Role;
import com.vkr.kampot_podcast.models.User;
import com.vkr.kampot_podcast.repository.ReviewRepository;
import com.vkr.kampot_podcast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

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
        model.addAttribute("name", "world");
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model)
    {
        model.addAttribute("title", "Страница про нас");
        return "about";
    }





}
