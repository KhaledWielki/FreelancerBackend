package com.freelancerworld.service.Implementation;

import com.freelancerworld.model.Request;
import com.freelancerworld.repository.RequestRepository;
import com.freelancerworld.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Patron on 10.11.2017.
 */

@Service("requestService")
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request findRequestByTitle(String title) {
        return requestRepository.findByTitle(title);
    }

    @Override
    public Request findRequestById(long id) {
        return requestRepository.findById(id);
    }

    @Override
    public void saveRequest(Request request) {
        requestRepository.save(request);
    }

    @Override
    public List<Request> findAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public void changeStatus(long id) {
        Request tempRequest = requestRepository.findById(id);

        if(tempRequest.getActive() == 0) {
            tempRequest.setActive(1);
        }
        else {
            tempRequest.setActive(0);
        }
        requestRepository.save(tempRequest);
    }

    @Override
    public List<Request> findRequestsByUserTakerIdAndActive(int userTakerId, int active) {
        List<Request> finishedRequestsByUser = requestRepository.findRequestByRequestTakerIdAndActiveOrderByCreationDateDesc(userTakerId, active);
        if(finishedRequestsByUser == null) {
            return null;
        }
        else {
            return finishedRequestsByUser;
        }
    }
}
