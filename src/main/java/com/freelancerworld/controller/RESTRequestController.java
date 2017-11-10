package com.freelancerworld.controller;

import com.freelancerworld.model.Request;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Patron on 10.11.2017.
 */
@RestController
@RequestMapping("/request")
public class RESTRequestController {


        @Autowired
        private RequestServiceImpl requestService;

        @RequestMapping("/getall")
        public List<Request> findAll() {
            return requestService.findAllRequests();
        }
}
