package com.vkr.kampot_podcast.models;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "podcasts")
public class Podcast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String hosts;
    private String description;
    private String genre;
    private String podcastDuration;
    private String site;
    private String link;
    private String logoLink;



    @ManyToMany(mappedBy = "likedPodcasts")
    Set<User> likes;


    public Podcast() {}

    public Podcast(String name, String hosts, String description, String genre, String podcastDuration, String site, String link, String logoLink) {
        this.name = name;
        this.hosts = hosts;
        this.description = description;
        this.genre = genre;
        this.podcastDuration = podcastDuration;
        this.site = site;
        this.link = link;
        this.logoLink = logoLink;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHosts() {
        return hosts;
    }

    public void setHosts(String hosts) {
        this.hosts = hosts;
    }

    public String getPodcastDuration() {
        return podcastDuration;
    }

    public void setPodcastDuration(String podcastDuration) {
        this.podcastDuration = podcastDuration;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public void setLogoLink(String logoLink) {
        this.logoLink = logoLink;
    }
}
