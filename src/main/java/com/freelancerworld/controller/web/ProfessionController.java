package com.freelancerworld.controller.web;

import com.freelancerworld.model.Profession;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/admin/profession/edit/{id}")
    public String editProfession(@PathVariable int id, Model model) {
        Profession tempProfession= professionService.findProfessionById(id);
        model.addAttribute("profession", tempProfession);
        return "admin/professionform";
    }

    @RequestMapping(value = "profession", method = RequestMethod.POST)
    public String saveEditedProfession(Profession profession) {
        professionService.saveProfession(profession);
        return "redirect:/admin/profession/show/" + profession.getId();
    }

    @RequestMapping(value = "admin/profession/show/{id}")
    public String showProfession(@PathVariable int id, Model model) {
        Profession tempProfession = professionService.findProfessionById(id);
        model.addAttribute("profession", tempProfession);
        return "admin/professionshow";
    }

    @RequestMapping("admin/profession/new")
    public String newProfession(Model model){
        model.addAttribute("profession", new Profession());
        return "admin/professionform";
    }
    @RequestMapping(value = "admin/profession/delete/{id}")
    public String deleteProfession(@PathVariable int id) {
        professionService.deleteProfessionById(id);
        return "redirect:/admin/professions";
    }
}
