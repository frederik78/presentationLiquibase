package com.techsolcom.carnet.services;

import com.techsolcom.carnet.dao.PersonneRepository;
import com.techsolcom.dto.carnet.AdresseContact;
import com.techsolcom.dto.carnet.Personne;
import com.techsolcom.dto.carnet.TypeContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Frederic on 04/05/15.
 */
@Service
public class ServicePersonne {

    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> findAllPersonnes()
    {
        final List<Personne> result = new ArrayList<>(5);
        for(int i =0; i<5; i++)
        {
            result.add(genereMockPersonne());
        }

          return result;
//        return personneRepository.findAll();
    }

    public Personne findPersonAndContactAndProvider(Integer id)
    {

        return genereMockPersonne();
//        return personneRepository.findPersonAndContactAndProvider(id);
    }

    private Personne genereMockPersonne() {
        final Personne personne = new Personne();
        personne.setId(1);
        personne.setNaissance(new Date());
        personne.setNom("Nom Bouchon");
        personne.setPrenom("Prenom Bouchon");
        final TypeContact typeContact = new TypeContact();
        typeContact.setNom("Type Contact");
        typeContact.setId(1);
        final AdresseContact adresseContact = new AdresseContact();
        adresseContact.setLibelle("Adresse Test");
        adresseContact.setId(1);
        adresseContact.setPersonne(personne);
        personne.setAdressesContact(Arrays.asList(adresseContact));
        adresseContact.setTypeContact(typeContact);
        return personne;
    }
}
