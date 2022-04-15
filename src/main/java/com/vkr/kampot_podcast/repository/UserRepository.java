package com.vkr.kampot_podcast.repository;

import com.vkr.kampot_podcast.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
    User findByUsername(String username);
}
