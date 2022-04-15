package com.vkr.kampot_podcast.repository;

import com.vkr.kampot_podcast.models.Reviews;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Reviews, Long>
{

}
