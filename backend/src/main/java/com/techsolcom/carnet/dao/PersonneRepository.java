package com.techsolcom.carnet.dao;

import com.techsolcom.dto.carnet.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Frederic on 03/05/15.
 */

public interface PersonneRepository extends JpaRepository<Personne, Integer>{

    @Query("Select personne from Personne personne join fetch personne.adressesContact adresse join fetch adresse.typeContact join fetch personne.pays  where personne.id = :id")
    Personne findPersonAndContactAndProvider(@Param(value = "id") Integer id);
}
