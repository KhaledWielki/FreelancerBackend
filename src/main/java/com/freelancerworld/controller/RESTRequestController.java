package com.freelancerworld.controller;

import com.freelancerworld.model.*;
import com.freelancerworld.model.contexts.UserAddressRequestProfessionContext;
import com.freelancerworld.service.Implementation.AddressServiceImpl;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import com.freelancerworld.service.Implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * Created by Patron on 10.11.2017.
 */
@RestController
@RequestMapping("/request")
public class RESTRequestController {

    private int YES = 1;
    private int NO = 0;

    @Autowired
    private RequestServiceImpl requestService;

    @Autowired
    private AddressServiceImpl addressService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ProfessionServiceImpl professionService;

    @RequestMapping("/getall")
    public List<Request> findAll() {
        return requestService.findAllRequests();
    }

    @RequestMapping(value = "/getrequest/{requestId}")
    public Request getSelectedRequest(@PathVariable long requestId) {
            Request tempRequest = requestService.findRequestById(requestId);
            return tempRequest;
    }

    @RequestMapping(value = "/getrequests/{userId}")
    public List<Request> getMyRequests(@PathVariable int userId) {
        List<Request> requests = requestService.findAllRequests();
        List<Request> myRequests = new ArrayList<>();
        for (Request req : requests) {
            if(req.getUser().getId() == userId) {
                myRequests.add(req);
            }
        }
        return myRequests;
    }

    @RequestMapping(value = "/newrequest", method = RequestMethod.POST)
    public @ResponseBody Message addRequest(@RequestBody UserAddressRequestProfessionContext context) {
            addressService.saveAddress(context.getAddress());

            User tempUser = userService.findUserById(context.getUser().getId());
            Profession tempProfession = professionService.findProfessionByName(context.getProfession().getName());
            int minPayment = context.getRequest().getMinPayment();
            int maxPayment = context.getRequest().getMaxPayment();

            if(minPayment > maxPayment) {
                return new Message(202, "Min payment cannot be greater than max payment");
            }
            else {
                context.getRequest().setAddress(context.getAddress());
                context.getRequest().setUser(tempUser);
                context.getRequest().setProfession(tempProfession);
                context.getRequest().setActive(YES);
                context.getRequest().setRequestTakerId(0);
                context.getRequest().setMark(0);

                LocalDate todayLocalDate = LocalDate.now();
                java.sql.Date sqlDate = java.sql.Date.valueOf(todayLocalDate);

                context.getRequest().setCreationDate(sqlDate);

                requestService.saveRequest(context.getRequest());
                return new Message(201, "Success!");
            }
    }


    /**
     *
     * @param requestId
     * @param requestTakerId
     * @return new Message
     *
     */
    @RequestMapping(value = "/addrequesttaker/{requestId}/{requestTakerId}", method = RequestMethod.POST)
    public Message addRequestTakerToRequest(@PathVariable int requestId, @PathVariable int requestTakerId) {
            Request tempRequest = requestService.findRequestById(requestId);

            if(userService.findUserById(requestTakerId) != null) {
                tempRequest.setRequestTakerId(requestTakerId);
                requestService.saveRequest(tempRequest);
                User requestTaker = userService.findUserById(requestTakerId);
                return new Message(201, "Great! You selected " + requestTaker.getName() + " " + requestTaker.getLastName());
            } else {
                return new Message(202, "Sorry, something bad happened");
            }
    }

    @RequestMapping(value = "/showcontractors/{requestId}", method = RequestMethod.POST)
    public Set<User> showContractors(@PathVariable("requestId") int requestId) {
        Request request = requestService.findRequestById(requestId);
        Set<User> contractors = request.getContractors();

        return contractors;
    }

    @RequestMapping(value="/setmarkforrequest/{requestId}/{mark}",method = RequestMethod.PATCH)
    public Message setMarkForRequest(@PathVariable int requestId, @PathVariable int mark) {
        Request request = requestService.findRequestById(requestId);
        if (request == null) {
            return new Message(404, "Failure, request does not exist");
        } else {
            request.setMark(mark);
            request.setActive(0);
            requestService.saveRequest(request);

            List<Request> requests = requestService.findRequestsByUserTakerId(request.getRequestTakerId());
            User user = userService.findUserById(request.getRequestTakerId());

            int size = 0;
            int sum = 0;
            double average;

            for (Request req : requests) {
                if(req.getMark() != 0) {
                    size++;
                    sum = sum + req.getMark();
                }
            }
            average = (double) sum / (double) size;
            average = round(average, 2);

            user.setAverageMark(average);
            userService.saveEditedUser(user);

            return new Message(200, "Success");
        }
    }

    @RequestMapping(value = "/gettaken/{requestTakerId}")
    public List<Request> getTakenRequestsByMe(@PathVariable int requestTakerId) {
        List<Request> allRequests = requestService.findAllRequests();
        List<Request> takenRequests = new ArrayList<>();

        for (Request req : allRequests) {
            if(req.getRequestTakerId() == requestTakerId) {
                takenRequests.add(req);
            }
        }
        return takenRequests;
    }

    @RequestMapping(value = "/editdescription", method = RequestMethod.PATCH)
    public @ResponseBody Message editDescriptionOfRequest(@RequestBody Request requestToEdit) {
        Request tempRequest = requestService.findRequestById(requestToEdit.getId());
        if(tempRequest == null) {
            return new Message(202, "Failure, request doesn't exist");
        }
        else {
            tempRequest.setDescription(requestToEdit.getDescription());
            requestService.saveRequest(tempRequest);
            return new Message(201, "Success");
        }
    }

    @RequestMapping(value = "/countcontractors", method = RequestMethod.POST)
    public @ResponseBody Map<Integer, Integer> countContractorsForRequest(@RequestBody List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (Integer id : list) {
            Request request = requestService.findRequestById(id);
            map.put(id, request.getContractors().size());
        }
        return map;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}