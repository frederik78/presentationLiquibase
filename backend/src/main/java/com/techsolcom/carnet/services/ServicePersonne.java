package com.techsolcom.carnet.services;

import com.techsolcom.carnet.dao.PaysRepository;
import com.techsolcom.carnet.dao.PersonneRepository;
import com.techsolcom.dto.carnet.Pays;
import com.techsolcom.dto.carnet.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Frederic on 04/05/15.
 */
@Service
public class ServicePersonne {

    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> findAllPersonnes()
    {
        return personneRepository.findAll();
    }

    public Personne findPersonAndContactAndProvider(Integer id)
    {
        return personneRepository.findPersonAndContactAndProvider(id);
    }
}
