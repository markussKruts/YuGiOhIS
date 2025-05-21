package jtt.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jtt.test.dao.impl.UserDAOImpl;
import jtt.test.dto.User;

@Controller
public class WebController {
	@Autowired
	UserDAOImpl service;
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
	@GetMapping("/")
	public String greeting(Model model) {
	model.addAttribute("message", "hello world");
	return "index";
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
