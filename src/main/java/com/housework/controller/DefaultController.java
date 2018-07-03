package com.housework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.housework.housework.Housework;
import com.housework.person.Person;
import com.housework.repository.HouseworkRepository;
import com.housework.repository.PersonRepository;

@Controller
public class DefaultController {

	@Autowired
	PersonRepository personrepository;
	
	@Autowired
	HouseworkRepository houseworkrepository;
	
    @GetMapping("/")
    public ModelAndView home1() {
    	ModelAndView homeView = new ModelAndView("/home");
    	List<Person> personrepositoryObject = this.personrepository.findAllByOrderByNameAsc();
    	List<Housework> houseworks = this.houseworkrepository.findAllByOrderByNameAsc();
    	homeView.addObject("persons", personrepositoryObject);
    	homeView.addObject("houseworks", houseworks);
        return homeView;
    }

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}