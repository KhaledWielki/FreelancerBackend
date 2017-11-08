package com.freelancerworld.controller;

import com.freelancerworld.model.Profession;
import com.freelancerworld.service.Implementation.ProfessionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Patryk on 2017-11-07.
 */

@RestController
@RequestMapping("/profession")
public class RESTProfessionController {

    @Autowired
    private ProfessionServiceImpl professionService;

    @RequestMapping("/getall")
    public List<Profession> findAll() {
        return professionService.findAllProfessions();
    }


}
