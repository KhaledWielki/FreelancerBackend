package com.freelancerworld.repository;

import com.freelancerworld.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Patron on 10.11.2017.
 */

@Repository("requestRepository")
public interface RequestRepository extends JpaRepository<Request, Integer> {

    Request findByTitle(String title);
    Request findById(long id);
    List<Request> findAll();
    List<Request> findRequestByRequestTakerIdAndActiveOrderByCreationDateDesc(int requestTakerId, int active);
    List<Request> findRequestByRequestTakerId(int userTakerId);

}
