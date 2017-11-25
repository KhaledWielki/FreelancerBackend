package com.freelancerworld.service;

import com.freelancerworld.model.Profession;

import java.util.List;

/**
 * Created by AdamR on 2017-10-29.
 */
public interface ProfessionService {

    public Profession findProfessionByName(String name);
    public Profession findProfessionById(int id);
    public void saveProfession(Profession profession);
    public List<Profession> findAllProfessions();
    public void deleteProfessionById(int id);
}
