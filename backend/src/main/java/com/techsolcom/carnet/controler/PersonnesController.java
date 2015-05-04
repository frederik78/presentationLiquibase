package com.techsolcom.carnet.controler;

import com.techsolcom.carnet.services.ServicePays;
import com.techsolcom.dto.carnet.Pays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    ServicePays servicePays;

    @ResponseBody
    @RequestMapping(value = "/countries", method = RequestMethod.GET, produces = "application/json")
    public List<Pays> showAllCountries() {
        return servicePays.findAllCountries();
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("message", "Spring 3 MVC Hello World");
        return "hello";
    }


}
