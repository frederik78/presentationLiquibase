package com.techsolcom.carnet.services;

import com.techsolcom.carnet.dao.PaysRepository;
import com.techsolcom.dto.carnet.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Frederic on 04/05/15.
 */
@Service
public class ServicePays {

    @Autowired
    private PaysRepository paysRepository;

    public long countCountries()
    {
        return paysRepository.count();
    }

    public List<Pays> findAllCountries()
    {
        final Sort sort = new Sort(Sort.Direction.ASC, "nom");
        return paysRepository.findAll(sort);
    }
}
