package com.freelancerworld.service;

import com.freelancerworld.model.Profession;

/**
 * Created by AdamR on 2017-10-29.
 */
public interface ProfessionService {

    public Profession findProfessionByName(String name);
    public void saveProfession(Profession profession);
}
