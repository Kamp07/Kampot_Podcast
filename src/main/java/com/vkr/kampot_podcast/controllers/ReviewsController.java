package com.vkr.kampot_podcast.controllers;

import com.vkr.kampot_podcast.models.Reviews;
import com.vkr.kampot_podcast.models.User;
import com.vkr.kampot_podcast.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ReviewsController {
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/reviews")
    public String reviews(Model model)
    {
        Iterable<Reviews> reviews = reviewRepository.findAll();
        model.addAttribute("title", "Написать отзыв...");
        model.addAttribute("reviews", reviews);
        return "reviews";
    }

    @PostMapping("/reviews-add")
    public String reviewsAdd(@AuthenticationPrincipal User user, @RequestParam String title, @RequestParam String text, Model model)
    {
        Reviews reviews = new Reviews(title, text, user);
        reviewRepository.save(reviews);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{id}")
    public String reviewsInfo(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Reviews> reviews = reviewRepository.findById(id);
        ArrayList<Reviews> result = new ArrayList<>();
        reviews.ifPresent(result::add);
        model.addAttribute("review", result);
        return "reviews-info";
    }

    @GetMapping("/reviews/{id}/update")
    public String reviewsUpdate(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Reviews> reviews = reviewRepository.findById(id);
        ArrayList<Reviews> result = new ArrayList<>();
        reviews.ifPresent(result::add);
        model.addAttribute("review", result);
        return "reviews-update";
    }

    @PostMapping("/reviews/{id}/update")
    public String reviewsUpdateForm(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String text, Model model) throws ClassNotFoundException
    {
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new ClassNotFoundException());
        reviews.setTitle(title);
        reviews.setText(text);
        reviewRepository.save(reviews);
        return "redirect:/reviews";
    }

    @PostMapping("/reviews/{id}/delete")
    public String reviewsDelete(@PathVariable(value = "id") long id, Model model) throws ClassNotFoundException
    {
        Reviews reviews = reviewRepository.findById(id).orElseThrow(() -> new ClassNotFoundException());
        reviewRepository.delete(reviews);
        return "redirect:/reviews";
    }
}
