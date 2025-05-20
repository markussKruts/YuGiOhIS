package jtt.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping("/")
	public String greeting(Model model) {
	model.addAttribute("message", "hello world");
	return "index";
	}
	
    @GetMapping("/signup")
	public String login(Model model) {
	model.addAttribute("message", "signup");
	return "signup";
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
