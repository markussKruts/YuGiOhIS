package jtt.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jtt.test.dao.Card_imageDAO;
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
 	public String signup(@RequestParam String username, @RequestParam String email, @RequestParam String name, @RequestParam String password, Model model, HttpSession session) {
    	if(service.getByUsername(username) != null) {
    		model.addAttribute("error", "username is taken");
    		return "redirect:/signup";
    }
    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    service.insert(user);
    session.setAttribute("loggedInUser", user);
 	return "redirect:/main";
    }
    @PostMapping("/login")
 	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
    	
    	if(service.getByUsername(username) != null) {
    		User user = service.getByUsername(username);
    		if(passwordEncoder.matches(password, user.getPassword())) {
    			session.setAttribute("loggedInUser", user);
    			return "redirect:/main";
    		}else {
    			model.addAttribute("error", "Invalid password.");
    		}
    	}else {
    		model.addAttribute("error", "Username not found.");
    	}
 	return "redirect:/index";
    }
	@GetMapping("/")
	public String greeting(Model model, HttpSession session) {
	model.addAttribute("message", "hello world");
	session.invalidate();
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
    
    
    //card view image filler
    @GetMapping("/frame-image/list")
    @ResponseBody
    public List<Map<String, Object>> listImageMetadata() {
        return imgService.getAllData().stream().map(img -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", img.getId());
            map.put("name", img.getName());
            map.put("content_type", img.getContent_type());
            return map;
        }).toList();
    }

    @GetMapping("/ownCards")
    public String getCards(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        List<Card> cards = cardService.getByUser(user);
        System.out.println("Cards found: " + cards.size()); // Debug
        for (Card c : cards) {
            System.out.println("Card: " + c.getName());
        }
        model.addAttribute("cards", cards);
        
        return "ownCards";
    }
    @GetMapping("/allCards")
    public String getAllCards(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        
        return "allCards";
    }
    // Return JSON list of image metadata
    @GetMapping("/type/list")
    @ResponseBody
    public List<Map<String, Object>> listType() {
        return typeService.getAllData().stream().map(typee -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", typee.getId());
            map.put("name", typee.getName());
            return map;
        }).toList();
    }
    
    // Return JSON list of image metadata
    @GetMapping("/arch/list")
    @ResponseBody
    public List<Map<String, Object>> listArch() {
        return archService.getAllData().stream().map(arch -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", arch.getId());
            map.put("name", arch.getName());
            return map;
        }).toList();
    }
    // Return JSON list of image metadata
    @GetMapping("/race/list")
    @ResponseBody
    public List<Map<String, Object>> listRace() {
        return raceService.getAllData().stream().map(race -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", race.getId());
            map.put("name", race.getName());
            return map;
        }).toList();
    }
    // Return JSON list of image metadata
    @GetMapping("/ftype/list")
    @ResponseBody
    public List<Map<String, Object>> listFtype() {
        return ftypeService.getAllData().stream().map(ftype -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", ftype.getFrame_type());
            map.put("name", ftype.getName());
            return map;
        }).toList();
    }
    // Return JSON list of image metadata
    @GetMapping("/attr/list")
    @ResponseBody
    public List<Map<String, Object>> listAttr() {
        return attrService.getAllData().stream().map(attr -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", attr.getId());
            map.put("name", attr.getName());
            return map;
        }).toList();
    }
    
    // Return image binary data
    @GetMapping("/frame-image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        Card_image img = imgService.getByID(id);
        if (img == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, img.getContent_type())
            .body(img.getImage());
    }
    
    // Return image binary data
    @PostMapping("/deleteCard/{id}")
    public ResponseEntity<Void> deletCard(@PathVariable int id) {
        Card card = cardService.getByID(id);
        if (card == null) return ResponseEntity.notFound().build();
        
        cardService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
	@GetMapping("/cardbuilder")
	public String build(Model model, @RequestParam(required = false) Integer id) {
	    if (id != null) {
	        Card card = cardService.getByID(id);
	        model.addAttribute("card", card);
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            String cardJson = objectMapper.writeValueAsString(card);
	            model.addAttribute("cardJson", cardJson);
	        } catch (Exception e) {
	            model.addAttribute("cardJson", "{}");
	        }

	    } else {
	    	Card card = new Card();
	        model.addAttribute("card", card);
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            String cardJson = objectMapper.writeValueAsString(card);
	            model.addAttribute("cardJson", cardJson);
	        } catch (Exception e) {
	            model.addAttribute("cardJson", "{}");
	        }

	    }
	return "cardbuilder";
	}
	@PostMapping("/submit-card")
	public String submitCard(        @RequestParam String name,
	        @RequestParam String desc,
	        @RequestParam int atk,
	        @RequestParam int def,
	        @RequestParam int lvl,
	        @RequestParam String arch,
	        @RequestParam String type,
	        @RequestParam String attr,
	        @RequestParam String race,
	        @RequestParam String ftype,
	        @RequestParam(value = "card-image", required = false) MultipartFile imageFile,
	        @RequestParam String image,
	        @RequestParam(required = false) Integer id,
	        HttpSession session) throws IOException, SQLException {

		Card card;
		if (id != null && id != 0) {
		    card = cardService.getByID(id);
		    if (card == null) {
		        // Fallback if the card doesn't exist — prevent insert
		        return "redirect:/card?error=CardNotFound";
		    }
		} else {
		    card = new Card();
		}
		
	    User userr = (User) session.getAttribute("loggedInUser");
	    if (userr == null) {
	        return "redirect:/login";
	    }
	    card.setName(name);
	    card.setDescription(desc);
	    card.setAttack(atk);
	    card.setDefense(def);
	    card.setLevel(lvl);
	    // Handle Archetype
	    Archetype archetype = archService.getByName(arch);
	    if (archetype == null) {
	        archetype = new Archetype(arch);
	        archService.insert(archetype);
	    }
	    card.setArchetype(archetype);

	    // Handle Card Type
	    Card_Type cardType = typeService.getByName(type);
	    if (cardType == null) {
	        cardType = new Card_Type(type);
	        typeService.insert(cardType);
	    }
	    card.setCard_type(cardType);

	    // Handle Attribute
	    Attribute attrr = attrService.getByName(attr);
	    if (attrr == null) {
	        attrr = new Attribute(attr);
	        attrService.insert(attrr);
	    }
	    card.setAttribute(attrr);

	    // Handle Race
	    Race cardRace = raceService.getByRace(race);
	    if (cardRace == null) {
	        cardRace = new Race(race);
	        raceService.insert(cardRace);
	    }
	    card.setRace(cardRace);

	    // Handle FrameType
	    FrameType frameType = ftypeService.getByName(ftype);
	    if (frameType == null) {
	        frameType = new FrameType(ftype);
	        ftypeService.insert(frameType);
	    }
	    card.setFrame_type(frameType);

	    // Handle User
	    User user = service.getByID(userr.getUser_id());
	    card.setUser(user);

	    // Handle Card Image
	    Card_image cardImage = imgService.getByName(image);
	    if (cardImage == null && imageFile != null && !imageFile.isEmpty()) {
	        cardImage = new Card_image();
	        cardImage.setName(image);
	        cardImage.setImage(imageFile.getBytes());
	        cardImage.setContent_type(imageFile.getContentType());
	        imgService.insert(cardImage);
	    }
	    card.setImage(cardImage);
	    
	    System.out.println("Name: " + name);
	    System.out.println("ATK: " + atk);
	    System.out.println("DEF: " + def);
	    System.out.println("Level: " + lvl);
	    System.out.println("Archetype: " + arch);
	    System.out.println("Type: " + type);
	    System.out.println("Attr: " + attr);
	    System.out.println("Race: " + race);
	    System.out.println("Ftype: " + ftype);
	    System.out.println("Image: " + image);
	    System.out.println("Image file: " + (imageFile != null ? imageFile.getOriginalFilename() : "null"));
	    if (id != null && id != 0) {
	        cardService.update(card, id);
	    } else {
	        cardService.insert(card);
	    }

	    return "redirect:/card";
	}

	@GetMapping("/proxy-image")
	public ResponseEntity<byte[]> proxyImage(@RequestParam String url) throws IOException {
	    RestTemplate restTemplate = new RestTemplate();
	    ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.IMAGE_JPEG); // or dynamic based on content-type
	    return new ResponseEntity<>(response.getBody(), headers, HttpStatus.OK);
	}
}

