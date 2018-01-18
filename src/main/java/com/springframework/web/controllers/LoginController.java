package com.springframework.web.controllers;

import com.springframework.web.model.Message;
import com.springframework.web.model.User;
import com.springframework.web.services.UsersService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @ResponseBody
    @GetMapping(value = "/getMessages",produces = "application/json")
    public Map<String, Object> getMessages(Principal principal) {

        List<Message> messages = null;

        if(principal == null) {
            messages = new ArrayList<>();
        } else {
            String username = principal.getName();
            messages = usersService.getMessages(username);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("messages", messages);
        data.put("number", messages.size());

        return data;
    }

    @ResponseBody
    @PostMapping(value = "/sendMessage",produces = "application/json")
    public Map<String, Object> sendMessage(Principal principal, @RequestBody Map<String, Object> data) {

        String text = (String) data.get("text");
        String name = (String) data.get("name");
        String email = (String) data.get("email");
        int target = (int) data.get("target");

        System.out.println(name + ":" + email + ":" + text);

        Map<String, Object> rval = new HashMap<>();
        rval.put("success", true);
        rval.put("target", target);

        return rval;
    }

    @GetMapping("/messages")
    public String showMessages() {
        return "messages";
    }
}
