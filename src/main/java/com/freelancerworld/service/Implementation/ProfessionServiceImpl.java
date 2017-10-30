package com.freelancerworld.service.Implementation;

import com.freelancerworld.model.Profession;
import com.freelancerworld.repository.ProfessionRepository;
import com.freelancerworld.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by AdamR on 2017-10-29.
 */

@Service("professionService")
public class ProfessionServiceImpl implements ProfessionService {

    @Autowired
    private ProfessionRepository professionRepository;

    @Override
    public Profession findProfessionByName(String name) {
        return professionRepository.findByName(name);
    }

    @Override
    public void saveProfession(Profession profession) {
        professionRepository.save(profession);
    }
}
