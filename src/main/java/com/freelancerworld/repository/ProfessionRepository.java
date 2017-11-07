package com.freelancerworld.repository;

import com.freelancerworld.model.Profession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by AdamR on 2017-10-29.
 */

@Repository("professionRepository")
public interface ProfessionRepository extends JpaRepository<Profession, Integer> {

    Profession findByName(String name);
    List<Profession> findAll();
}
