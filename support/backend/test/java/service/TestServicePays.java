package com.techsolcom.carnet.services;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.extractProperty;

import com.techsolcom.carnet.DBTest;
import com.techsolcom.dto.carnet.Pays;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Frederic on 04/05/15.
 */
public class TestServicePays extends DBTest {

    @Autowired
    private ServicePays servicePays;

    @Test
    public void find_all_countries()
    {
        final List<Pays> pays =  servicePays.findAllCountries();
        assertThat(extractProperty("nom").from(pays)).contains("CANADA", "FRANCE", "GUADELOUPE");
        assertThat(pays).hasSize(243);
    }

}
