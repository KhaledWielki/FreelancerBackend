package com.freelancerworld.controller;

import com.freelancerworld.model.Profession;
import com.freelancerworld.model.Request;
import com.freelancerworld.model.User;
import com.freelancerworld.model.UserProfessionContext;
import com.freelancerworld.service.Implementation.RequestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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

        /**TODO
         * Api zwracajnące wszystkie zlecenia wymagające profesji jaką użytkownik posiada
         */

        @PostMapping(value = "/findrequests")
        @ResponseBody
        public List<Request> findRequests (@RequestBody User user) {
                List<Request> userRequests = new ArrayList<Request>();
                Set<Profession> userProfessions = user.getProfessions();
                int userProfessionsCount = userProfessions.size();

                Iterator iterator = userProfessions.iterator();

                for(int i = 0; i < userProfessionsCount; i++) {
                        Profession profession = (Profession) iterator.next();
                        userRequests.add((Request) requestService.findByProfession(profession));
                }
                return userRequests;
        }
}
