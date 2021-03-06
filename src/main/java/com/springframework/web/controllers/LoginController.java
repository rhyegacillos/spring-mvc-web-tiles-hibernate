package com.springframework.web.controllers;

import com.springframework.web.model.User;
import com.springframework.web.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    private UsersService usersService;

    @Autowired
    public LoginController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/newAccount")
    public String showNewAccount(Model model) {

        model.addAttribute("user", new User());
        return "newAccount";
    }

    @PostMapping("/createAccount")
    public String createNewAccount(Model model, @ModelAttribute("user") @Valid User user, BindingResult result) {

        if(result.hasErrors()) {

            return "newAccount";
        }

        user.setAuthority("ROLE_USER");
        user.setEnabled(true);

        if(usersService.exists(user.getUsername())){
            result.rejectValue("username", "DuplicateKey.user.username");
            return "newAccount";
        }

        usersService.createUser(user);

        return "accountCreated";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @GetMapping("/admin")
    public String showAdmin(Model model) {

        List<User> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/denied")
    public String showDeniedPage() {
        return "accessDenied";
    }
}
