package com.techsolcom.carnet.dao;

import com.techsolcom.dto.carnet.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Frederic on 03/05/15.
 */

public interface PersonneRepository extends JpaRepository<Personne, Integer>{

}
