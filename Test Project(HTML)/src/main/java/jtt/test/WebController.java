package jtt.test;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jtt.test.dao.impl.ArchetypeDAOImpl;
import jtt.test.dao.impl.AttributeDAOImpl;
import jtt.test.dao.impl.CardDAOImpl;
import jtt.test.dao.impl.Card_imageDAOImpl;
import jtt.test.dao.impl.Card_typeDAOImpl;
import jtt.test.dao.impl.Frame_typeDAOImpl;
import jtt.test.dao.impl.RaceDAOImpl;
import jtt.test.dao.impl.UserDAOImpl;
import jtt.test.dto.Archetype;
import jtt.test.dto.Attribute;
import jtt.test.dto.Card;
import jtt.test.dto.Card_Type;
import jtt.test.dto.Card_image;
import jtt.test.dto.FrameType;
import jtt.test.dto.Race;
import jtt.test.dto.User;
import jtt.test.repositories.ArchetypeRepository;
import jtt.test.repositories.AttributeRepository;
import jtt.test.repositories.Card_imageRepository;
import jtt.test.repositories.Card_typeRepository;
import jtt.test.repositories.Frame_typeRepository;
import jtt.test.repositories.RaceRepository;

@Controller
public class WebController {
	@Autowired
	UserDAOImpl service;
	@Autowired
	CardDAOImpl cardService;
	
	@Autowired
	ArchetypeDAOImpl archService;
	@Autowired
	AttributeDAOImpl attrService;
	@Autowired
	Card_imageDAOImpl imgService;
	@Autowired
	Card_typeDAOImpl typeService;
	@Autowired
	Frame_typeDAOImpl ftypeService;
	@Autowired
	RaceDAOImpl raceService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
    @GetMapping("/signup")
	public String sign(Model model) {
	model.addAttribute("message", "signup");
	return "signup";
    }
    
    @PostMapping("/submit")
 	public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String name, @RequestParam String password, Model model) {
    	if(service.getByUsername(username) != null) {
    		model.addAttribute("error", "username is taken");
    		return "signup";
    }
    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    service.insert(user);
 	return "main";
    }
    @PostMapping("/login")
 	public String login(@RequestParam String username, @RequestParam String password, Model model) {
    	
    	if(service.getByUsername(username) != null) {
    		User user = service.getByUsername(username);
    		if(passwordEncoder.matches(password, user.getPassword())) {
    			return "main";
    		}else {
    			model.addAttribute("error", "Invalid password.");
    		}
    	}else {
    		model.addAttribute("error", "Username not found.");
    	}
 	return "index";
    }
    @PostMapping("/submit-card")
 	public String submitCard(@RequestParam String name, @RequestParam String type, @RequestParam String desc, @RequestParam String arch,
 			@RequestParam String attribute, @RequestParam int atk, @RequestParam int def, @RequestParam int lvl,
 			@RequestParam String race, @RequestParam String frametype, @RequestParam("card-image") MultipartFile imageFile) throws SQLException, IOException {
    	
    	Archetype archetype;Card_Type cardType;Attribute attr;Card_image cardImage;FrameType frameType;
    	Race cardRace;
    	if(cardService.getByName(name) == null) {
    		byte[] image = imageFile.getBytes();
    		if(archService.getByName(arch) != null) {
    			archetype = archService.getByName(arch);
    		}else {
    			archetype = new Archetype(arch);
    			archService.insert(archetype);
    		}
    		if(typeService.getByName(type) != null) {
    			cardType = typeService.getByName(type);
    		}else {
    			cardType = new Card_Type(type);
    			typeService.insert(cardType);
    		}
    		if(attrService.getByName(attribute) != null) {
    			attr = attrService.getByName(attribute);
    		}else {
    			attr = new Attribute(attribute);
    			attrService.insert(attr);
    		}
    		if(imgService.getByImage(image) != null) {
    			cardImage = imgService.getByImage(image);
    		}else {
    			cardImage = new Card_image(image);
    			imgService.insert(cardImage);
    		}
    		if(ftypeService.getByName(frametype) != null) {
    			frameType = ftypeService.getByName(frametype);
    		}else {
    			frameType = new FrameType(frametype);
    			ftypeService.insert(frameType);
    		}
    		if(raceService.getByRace(race) != null) {
    			cardRace = raceService.getByRace(race);
    		}else {
    			cardRace = new Race(race);
    			raceService.insert(cardRace);
    		}
    		
    		Card card = new Card();
    		card.setArchetype(archetype);card.setAttribute(attr);card.setCard_type(cardType);
    		card.setFrame_type(frameType);card.setImage(cardImage);card.setRace(cardRace);
    		card.setName(name);card.setDescription(desc);card.setAttack(atk);card.setDefense(def);
    		card.setLevel(lvl);
    		
    		cardService.insert(card);
    		
    	}
 	return "card";
    }
	@GetMapping("/")
	public String greeting(Model model) {
	model.addAttribute("message", "hello world");
	return "index";
	}
	@GetMapping("/cardbuilder")
	public String build(Model model) {
	model.addAttribute("message", "hello world");
	return "cardbuilder";
	}
    @GetMapping("/main")
	public String main(Model model) {
	model.addAttribute("message", "signup");
	return "main";
    }
    @GetMapping("/card")
	public String card(Model model) {
	model.addAttribute("message", "signup");
	return "card";
    }
}
