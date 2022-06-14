

package com.vkr.kampot_podcast.repository;

import com.vkr.kampot_podcast.models.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReviewRepository extends JpaRepository<Reviews, Long>
{

}
