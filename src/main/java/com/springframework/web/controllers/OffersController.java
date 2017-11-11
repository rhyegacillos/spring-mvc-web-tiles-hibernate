package com.springframework.web.controllers;

import com.springframework.web.model.Offer;
import com.springframework.web.services.OffersService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class OffersController {

    OffersService offersService;

    public OffersController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping("/createOffer")
    public String createOffer(Model model, Principal principal) {

        Offer offer = null;

        if (principal != null) {
            String username = principal.getName();
            offer = offersService.getOffer(username);
        }

        if (offer == null)
            offer = new Offer();

        model.addAttribute("offer", offer);

        return "createOffer";
    }

    @PostMapping("/doCreate")
    public String doCreate(Model model, @ModelAttribute("offer") @Valid Offer offer, BindingResult result,
                           Principal principal, @RequestParam(value = "delete", required = false) String delete) {

        if (result.hasErrors()) {
            return "createOffer";
        }

        if (delete == null) {
            String username = principal.getName();
            offer.getUser().setUsername(username);
            offersService.saveOrUpdate(offer);
        } else
            offersService.delete(offer.getId());



        return "redirect:/";
    }
}
