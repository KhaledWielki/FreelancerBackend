package com.freelancerworld.repository;

import com.freelancerworld.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AdamR on 2017-10-29.
 */

@Repository("professionRepository")
public interface ProfessionReposiotory extends JpaRepository<Profession, Integer> {

    Profession findByName(String name);
}
