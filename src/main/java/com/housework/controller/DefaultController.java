package com.housework.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.housework.error.MyAccessDeniedHandler;
import com.housework.history.History;
import com.housework.housework.Housework;
import com.housework.housework.HouseworkSelect;
import com.housework.person.Person;
import com.housework.repository.HistoryRepository;
import com.housework.repository.HouseworkRepository;
import com.housework.repository.PersonRepository;

@Controller
public class DefaultController {
	private static Logger log = LoggerFactory.getLogger(DefaultController.class);
	
	List<Person> personrepositoryObject;
	@Autowired
	PersonRepository personrepository;
	
	@Autowired
	HouseworkRepository houseworkrepository;
	
	@Autowired
	HistoryRepository historyrepository;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
	
    @GetMapping("/")
    public ModelAndView home1() {
    	log.info("OLEN!");
    	ModelAndView homeView = new ModelAndView("/home");
    	List<Person> personrepositoryObject = this.personrepository.findAllByOrderByNameAsc();
    	List<Housework> houseworks = this.houseworkrepository.findAllByOrderByNameAsc();
    	homeView.addObject("persons", personrepositoryObject);
    	homeView.addObject("houseworks", houseworks);
    	homeView.addObject("person", new Person());
    	homeView.addObject("housework", new Housework());
    	homeView.addObject("houseworkSelect", new HouseworkSelect());
        return homeView;
    }
    
    @RequestMapping(value = "createperson", method = RequestMethod.POST)
    public String  createPerson(@Valid Person prsn, BindingResult bindingresult, RedirectAttributes attributes) {
    	if (bindingresult.hasErrors()) {
    		attributes.addFlashAttribute("nameError", "nameError");
    		return "redirect:/";
    	}
    		
    	
    	else {
    		personrepository.save(prsn);
    		attributes.addFlashAttribute("nameSuccess", "nameSuccess");
    		return "redirect:/";
    	}
    	
    }
    
    @RequestMapping(value = "createhousework", method = RequestMethod.POST)
    public String  createHousework(@Valid Housework housework, BindingResult bindingresult, RedirectAttributes attributes) {
    	if (bindingresult.hasErrors()) {
    		attributes.addFlashAttribute("houseworkError", "houseworkError");
    		return "redirect:/";
    	}
    		
    	
    	else {
    		houseworkrepository.save(housework);
    		attributes.addFlashAttribute("houseworkSuccess", "houseworkSuccess");
    		return "redirect:/";
    	}
    	
    }
    
    
    @RequestMapping(value ="deleteperson/{id}", method = RequestMethod.GET)
    public String deletePerson(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
    	personrepository.delete(personrepository.findByid(id));
    	return "redirect:/";
    }
    
    @RequestMapping(value ="deletehousework/{id}", method = RequestMethod.GET)
    public String deleteHousework(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
    	houseworkrepository.delete(houseworkrepository.findByid(id));
    	return "redirect:/";
    }
    
    @RequestMapping(value ="/personAddHousework", method = RequestMethod.POST)
    public String personAddHousework(@Valid HouseworkSelect select, BindingResult bindingresult, RedirectAttributes attributes) {
    	if (bindingresult.hasErrors()) {
    		attributes.addFlashAttribute("personhouseworkError", "personhouseworkError");
    		return "redirect:/";
    	}
    	
    	// TODO: Error handling
    	try {
	    	Person person = personrepository.findByid(select.getId()).get(0);
	    	int sum = person.getPoints();
	    	for (int i : select.getSelect()) {
	    		
	    		Housework work = houseworkrepository.findByid(i).get(0);
	    		History history = new History();
	        	history.setDate(LocalDateTime.now().format(formatter));
	        	history.setName(work.getName());
	        	history.setPersonId(person.getId());
	        	history.setType("Kodutoo");
	        	history.setPoints(work.getPoints());
	        	historyrepository.save(history);
	    		sum += work.getPoints();
	    	}
	    	person.setPoints(sum);
	    	personrepository.save(person);
    	} catch (Error e) {
    		log.info("Läks katki");
    		attributes.addFlashAttribute("personhouseworkError", "personhouseworkError");
    		return "redirect:/";
    	}
    	
    	attributes.addFlashAttribute("personhouseworkSuccess", "personhouseworkSuccess");
    	return "redirect:/";
    }
    
    @RequestMapping(value ="/reducepersonhousework/{id}", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody  Person reducePersonHousework(@PathVariable("id") int id, @RequestBody int points, RedirectAttributes attributes) {
    	log.info("PALUN VAATA");
    	log.info("ID: "+points);
    	Person person = personrepository.findByid(id).get(0);
    	History history = new History();
    	history.setDate(LocalDateTime.now().format(formatter));
    	history.setName("M2ngimine");
    	history.setPersonId(id);
    	history.setType("M2ng");
    	history.setPoints(points);
    	
    	historyrepository.save(history);
    	
    	
    	person.setPoints(person.getPoints()-points);
    	personrepository.save(person);
    	
        return person;
    	
    	
    }
    
	@RequestMapping(value="getperson/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody Person getPerson(@PathVariable("id") int id, RedirectAttributes attributes) {
		Person person = personrepository.findByid(id).get(0);
		log.info("TESTMEETOD");
		log.info(person.getName());

			return person;
	}
	
	@RequestMapping(value="changehousework/{id}", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody Housework changeHousework(@PathVariable("id") int id, @RequestBody int points, RedirectAttributes attributes) {
		Housework housework = houseworkrepository.findByid(id).get(0);
		housework.setPoints(points);
		houseworkrepository.save(housework);

		return housework;
	}
	
	@RequestMapping(value="history/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<History> getHistory(@PathVariable("id") int id, RedirectAttributes attributes) {
		List<History> personHistory = historyrepository.findBypersonId(id);
		
		return personHistory;
	}
   
    /*
     * Kasutu jama!
     */
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