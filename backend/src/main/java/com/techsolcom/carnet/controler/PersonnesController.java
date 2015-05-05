package com.techsolcom.carnet.controler;

import com.techsolcom.carnet.services.ServicePays;
import com.techsolcom.carnet.services.ServicePersonne;
import com.techsolcom.dto.carnet.Pays;
import com.techsolcom.dto.carnet.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Frederic on 04/05/15.
 */
@Controller
public class PersonnesController {

    @Autowired
    private ServicePays servicePays;

    @Autowired
    private ServicePersonne servicePersonne;

    @ResponseBody
    @RequestMapping(value = "/countries", method = RequestMethod.GET, produces = "application/json")
    public List<Pays> showAllCountries() {
        return servicePays.findAllCountries();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap model) {
        final List<Personne> personnes = servicePersonne.findAllPersonnes();
        model.addAttribute("personnes", personnes);
        return "hello";
    }

    @RequestMapping(value = "/contact/{id}")
    public String getContact(ModelMap model, @PathVariable Integer id)
    {
        model.addAttribute("personne", servicePersonne.findPersonAndContactAndProvider(id));
        return "contact";
    }


}
