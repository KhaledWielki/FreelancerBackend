package com.freelancerworld.controller.web;

import com.freelancerworld.model.Request;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestServiceImpl requestService;


    @RequestMapping(value = "/admin/requests", method = RequestMethod.GET)
    public String requestsList(Model model) {
        List<Request> requestList = requestService.findAllRequests();
        model.addAttribute("requestslist", requestList);
        return "admin/requests";
    }
}
