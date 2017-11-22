package com.freelancerworld.service;

import com.freelancerworld.model.Request;

import java.util.List;

/**
 * Created by Patron on 10.11.2017.
 */
public interface RequestService {

    public Request findRequestByTitle(String title);
    public Request findRequestById(long id);
    public void saveRequest(Request request);
    public List<Request> findAllRequests();
    public void changeStatus(long id);
}
