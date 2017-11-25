package com.freelancerworld.controller.web;

import com.freelancerworld.model.Profession;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProfessionController {

    @Autowired
    ProfessionServiceImpl professionService;

    @RequestMapping(value = "/admin/professions", method = RequestMethod.GET)
    public String professionsList(Model model) {
        List<Profession> professionList= professionService.findAllProfessions();
        model.addAttribute("professionslist", professionList);
        return "admin/professions";
    }
}
