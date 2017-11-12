package com.freelancerworld.service;

import com.freelancerworld.model.Profession;
import com.freelancerworld.model.Request;

import java.util.List;

/**
 * Created by Patron on 10.11.2017.
 */
public interface RequestService {

    public Request findRequestByTitle(String title);
    public void saveRequest(Request request);
    public List<Request> findAllRequests();
    public List<Request> findByProfession(Profession profession);
}
