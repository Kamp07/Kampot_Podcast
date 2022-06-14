package com.vkr.kampot_podcast.controllers;

import com.vkr.kampot_podcast.models.Podcast;
import com.vkr.kampot_podcast.models.Role;
import com.vkr.kampot_podcast.models.User;
import com.vkr.kampot_podcast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/reg")
    public String reg()
    {
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(User user, Model model)
    {
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/user")
    public String UserList(Model model)
    {
        model.addAttribute("title","Все пользователи");
        model.addAttribute("users", userRepository.findAll());
        return "userList";
    }

    @GetMapping("/user/{id}")
    public String userUpdateForm(@PathVariable(value = "id") long id, Model model)
    {
        Optional<User> users = userRepository.findById(id);
        ArrayList<User> user = new ArrayList<>();
        users.ifPresent(user::add);
        model.addAttribute("user", user);
        model.addAttribute("role", Role.values());
        return "userUpdate";
    }
    @PostMapping("/user/{id}")
    public String userSave(@PathVariable(value = "id") long id, @RequestParam Map<String, String> form, @RequestParam String userName) throws ClassNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new ClassNotFoundException());
        user.setUsername(userName);
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet())
        {
            if(roles.contains(key))
            {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @GetMapping("/user/{login}/podcastLibrary")
    public String podcastLibrary(@PathVariable(value = "login") String login, Model model) {
        User user = userRepository.findByUsername(login);
        Set<Podcast> podcasts =  user.getLikedPodcasts();
        model.addAttribute("likedPodcasts", podcasts);
        return "podcastLibrary";
    }
}
