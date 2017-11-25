package com.freelancerworld.controller.web;

import com.freelancerworld.model.Request;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/admin/requests/changestatus/{id}")
    public String changeStatus(@PathVariable long id) {
        requestService.changeStatus(id);
        return "redirect:/admin/requests";
    }

    @RequestMapping(value = "/admin/requests/edit/{id}")
    public String editRequest(@PathVariable int id, Model model) {
        Request tempRequest = requestService.findRequestById(id);
        model.addAttribute("request", tempRequest);
        return "admin/requestform";
    }

    @RequestMapping(value = "request", method = RequestMethod.POST)
    public String saveEditedRequest(Request request) {
        if(requestService.findRequestById(request.getId())!= null) {
            requestService.saveRequest(request);
        }
        return "redirect:/admin/request/show/" + request.getId();
    }

    @RequestMapping(value = "admin/request/show/{id}")
    public String showRequest(@PathVariable int id, Model model) {
        Request tempRequest = requestService.findRequestById(id);
        model.addAttribute("request", tempRequest);
        return "admin/requestshow";
    }
}
