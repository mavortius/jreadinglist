package br.com.mavortius.jreadinglist.controller;

import br.com.mavortius.jreadinglist.domain.User;
import br.com.mavortius.jreadinglist.security.SecurityService;
import br.com.mavortius.jreadinglist.service.UserService;
import br.com.mavortius.jreadinglist.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator validator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm,
                               BindingResult bindingResult,
                               Model model) {
        validator.validate(userForm, bindingResult);

        if(bindingResult.hasErrors()) {
            return "registration";
        }

        service.save(userForm);
        securityService.autologin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/readingList";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if(error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if(logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }
}
