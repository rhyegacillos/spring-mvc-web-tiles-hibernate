package com.springframework.web.controllers;

import com.springframework.web.model.Offer;
import com.springframework.web.services.OffersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private static Logger logger = Logger.getLogger(HomeController.class);

    OffersService offersService;

    @Autowired
    public HomeController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/")
    public String showHome(Model model, Principal principal) {

        List<Offer> offers = offersService.getCurrent();

        model.addAttribute("offers", offers);

        boolean hasOffer = false;

        if(principal != null) {
            hasOffer = offersService.hasOffer(principal.getName());
        }

        model.addAttribute("hasOffer", hasOffer);

        return "home";
    }

}
