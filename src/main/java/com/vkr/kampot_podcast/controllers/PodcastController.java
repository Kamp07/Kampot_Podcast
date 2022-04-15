package com.vkr.kampot_podcast.controllers;

import com.vkr.kampot_podcast.models.Podcast;
import com.vkr.kampot_podcast.models.Reviews;
import com.vkr.kampot_podcast.models.User;
import com.vkr.kampot_podcast.repository.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.*;

@Controller
public class PodcastController {
    @Autowired
    private PodcastRepository podcastRepository;

    @GetMapping("/podcasts")
    public String podcasts(Model model)
    {
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        model.addAttribute("title", "Podcasts");
        model.addAttribute("podcasts", podcasts);
        return "podcasts";
    }

    @PostMapping("/podcasts-add")
    public String reviewsAdd(@RequestParam String name, @RequestParam String hosts, @RequestParam String description,
                             @RequestParam String genre, @RequestParam String podcastDuration, @RequestParam String site, @RequestParam String link, @RequestParam String logoLink, Model model)
    {
        Podcast podcast = new Podcast(name, hosts, description, genre, podcastDuration, site, link, logoLink);
        podcastRepository.save(podcast);
        return "redirect:/podcasts";
    }

    @GetMapping("/podcasts/{id}")
    public String podcastInfo(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Podcast> podcasts = podcastRepository.findById(id);
        ArrayList<Podcast> podcast = new ArrayList<>();
        podcasts.ifPresent(podcast::add);
        model.addAttribute("podcast", podcast);
        return "podcasts-info";
    }


    @GetMapping("/podcasts/{id}/update")
    public String reviewsUpdate(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Podcast> podcasts = podcastRepository.findById(id);
        ArrayList<Podcast> podcast = new ArrayList<>();
        podcasts.ifPresent(podcast::add);
        model.addAttribute("podcast", podcast);
        return "podcasts-update";
    }


    @PostMapping("/podcasts/{id}/update")
    public String podcastUpdateForm(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String hosts, @RequestParam String description,
                                    @RequestParam String genre, @RequestParam String podcastDuration, @RequestParam String site, @RequestParam String link, @RequestParam String logoLink, Model model)
            throws ClassNotFoundException
    {
        Podcast podcast = podcastRepository.findById(id).orElseThrow(() -> new ClassNotFoundException());
        podcast.setName(name);
        podcast.setHosts(hosts);
        podcast.setDescription(description);
        podcast.setGenre(genre);
        podcast.setPodcastDuration(podcastDuration);
        podcast.setSite(site);
        podcast.setLink(link);
        podcast.setLogoLink(logoLink);
        podcastRepository.save(podcast);
        return "redirect:/podcasts";
    }

    @PostMapping("/podcasts/{id}/delete")
    public String reviewsDelete(@PathVariable(value = "id") long id, Model model) throws ClassNotFoundException
    {
        Podcast podcasts = podcastRepository.findById(id).orElseThrow(() -> new ClassNotFoundException());
        podcastRepository.delete(podcasts);
        return "redirect:/podcasts";
    }

    @GetMapping("/podcastsCom")
    public String podcastsComedy(Model model)
    {
        model.addAttribute("title", "PodcastsComedy");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> comedyPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
                if (podcast.getGenre().contains("Юмор"))
                {
                    comedyPodcasts.put(podcast.getName(), podcast.getLogoLink());
                }
        }
        model.addAttribute("comedyPodcasts", comedyPodcasts);
        return "podcasts-comedy";
    }

    @GetMapping("/podcastsArts")
    public String podcastsArts(Model model)
    {
        model.addAttribute("title", "PodcastsArts");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> artsPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Искусство"))
            {
                artsPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("artsPodcasts", artsPodcasts);
        return "podcasts-arts";
    }

    @GetMapping("/podcastsBusiness")
    public String podcastsBusiness(Model model)
    {
        model.addAttribute("title", "PodcastsBusiness");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> businessPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Бизнес"))
            {
                businessPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("businessPodcasts", businessPodcasts);
        return "podcasts-business";
    }

    @GetMapping("/podcastsEducation")
    public String podcastsEducation(Model model)
    {
        model.addAttribute("title", "PodcastsEducation");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> educationPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Образование"))
            {
                educationPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("educationPodcasts", educationPodcasts);
        return "podcasts-education";
    }

    @GetMapping("/podcastsScience")
    public String podcastsScience(Model model)
    {
        model.addAttribute("title", "PodcastsScience");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> sciencePodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Наука"))
            {
                sciencePodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("sciencePodcasts", sciencePodcasts);
        return "podcasts-science";
    }

    @GetMapping("/podcastsGovernment")
    public String podcastsGovernment(Model model)
    {
        model.addAttribute("title", "PodcastsGovernment");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> governmentPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Правительство"))
            {
                governmentPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("governmentPodcasts", governmentPodcasts);
        return "podcasts-government";
    }

    @GetMapping("/podcastsHealth")
    public String podcastsHealth(Model model)
    {
        model.addAttribute("title", "PodcastsHealth");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> healthPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Здоровье и Фитнес"))
            {
                healthPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("healthPodcasts", healthPodcasts);
        return "podcasts-health";
    }

    @GetMapping("/podcastsHistory")
    public String podcastsHistory(Model model)
    {
        model.addAttribute("title", "PodcastsHistory");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> historyPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("История"))
            {
                historyPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("historyPodcasts", historyPodcasts);
        return "podcasts-history";
    }

    @GetMapping("/podcastsMusic")
    public String podcastsMusic(Model model)
    {
        model.addAttribute("title", "PodcastsMusic");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> musicPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Музыка"))
            {
                musicPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("musicPodcasts", musicPodcasts);
        return "podcasts-music";
    }

    @GetMapping("/podcastsNews")
    public String podcastsNews(Model model)
    {
        model.addAttribute("title", "PodcastsNews");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> newsPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Новости"))
            {
                newsPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("newsPodcasts", newsPodcasts);
        return "podcasts-news";
    }

    @GetMapping("/podcastsSport")
    public String podcastsSport(Model model)
    {
        model.addAttribute("title", "PodcastsSport");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> sportPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("Спорт"))
            {
                sportPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("sportPodcasts", sportPodcasts);
        return "podcasts-sport";
    }

    @GetMapping("/podcastsTvFilms")
    public String podcastsTvFilms(Model model)
    {
        model.addAttribute("title", "PodcastsTvFilms");
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        HashMap<String, String> tvFilmsPodcasts = new HashMap<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getGenre().contains("TV и Фильмы"))
            {
                tvFilmsPodcasts.put(podcast.getName(), podcast.getLogoLink());
            }
        }
        model.addAttribute("tvPodcasts", tvFilmsPodcasts);
        return "podcasts-tv";
    }

    @GetMapping("/podcastsCom/{name}")
    public String PodcastsByName(@PathVariable(value = "name") String name, Model model)
    {
        Iterable<Podcast> podcasts = podcastRepository.findAll();
        ArrayList<Podcast> podcastByName = new ArrayList<>();
        for (Podcast podcast : podcasts)
        {
            if (podcast.getName().contains(name))
            {
                podcastByName.add(podcast);
            }
        }
        model.addAttribute("title", name);
        model.addAttribute("podcastsByName", podcastByName);
        return "podcastsByName";
    }
}
