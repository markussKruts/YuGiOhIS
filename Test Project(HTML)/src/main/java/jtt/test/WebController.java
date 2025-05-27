package jtt.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
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
    		return "signup";
    }
    User user = new User();
    user.setName(name);
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    user.setEmail(email);
    service.insert(user);
    session.setAttribute("loggedInUser", user);
 	return "main";
    }
    @PostMapping("/login")
 	public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
    	
    	if(service.getByUsername(username) != null) {
    		User user = service.getByUsername(username);
    		if(passwordEncoder.matches(password, user.getPassword())) {
    			session.setAttribute("loggedInUser", user);
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
	public String greeting(Model model, HttpSession session) {
	model.addAttribute("message", "hello world");
	session.invalidate();
	return "index";
	}
	@GetMapping("/cardbuilder")
	public String build(Model model) {
	model.addAttribute("message", "hello world");
	return "cardbuilder";
	}
	@GetMapping("/ownCards")
	public String ownCards(Model model) {
	model.addAttribute("message", "hello world");
	return "ownCards";
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
    
 // Return JSON list of image metadata
    @GetMapping("/frame-image/list")
    @ResponseBody
    public List<Map<String, Object>> listImageMetadata() {
        return imgService.getAllData().stream().map(img -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", img.getImage_id());
            map.put("name", img.getName());
            map.put("content_type", img.getContent_type());
            return map;
        }).toList();
    }
    
    // Return JSON list of image metadata
    @GetMapping("/type/list")
    @ResponseBody
    public List<Map<String, Object>> listType() {
        return typeService.getAllData().stream().map(typee -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", typee.getCard_typeId());
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
            map.put("id", arch.getArchetype_id());
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
            map.put("id", race.getRace_id());
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
            map.put("id", attr.getAttribute_id());
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
    
    @PostMapping("/submit-card")
    public String submitCard(
            @RequestParam String name,
            @RequestParam String type,
            @RequestParam String desc,
            @RequestParam String arch,
            @RequestParam String attr,
            @RequestParam int atk,
            @RequestParam int def,
            @RequestParam int lvl,
            @RequestParam String race,
            @RequestParam String ftype,
            @RequestParam(value = "card-image", required = false) MultipartFile imageFile,
            @RequestParam String image,
            HttpSession session
    ) throws SQLException, IOException {

        // Don't proceed if the card already exists
        if (cardService.getByName(name) != null) {
            return "card"; // Card already exists; skip insertion
        }

        // Archetype
        Archetype archetype = archService.getByName(arch);
        System.out.println(archService.getByName(arch));
        if (archetype == null) {
            archetype = new Archetype(arch);
            archService.insert(archetype);
        }

        // Card Type
        Card_Type cardType = typeService.getByName(type);
        if (cardType == null) {
            cardType = new Card_Type(type);
            typeService.insert(cardType);
        }

        // Attribute
        Attribute attrr = attrService.getByName(attr);
        if (attrr == null) {
            attrr = new Attribute(attr);
            attrService.insert(attrr);
        }

        // Frame Type
        FrameType frameType = ftypeService.getByName(ftype);
        if (frameType == null) {
            frameType = new FrameType(ftype);
            ftypeService.insert(frameType);
        }

        // Race
        Race cardRace = raceService.getByRace(race);
        if (cardRace == null) {
            cardRace = new Race(race);
            raceService.insert(cardRace);
        }
        
        // User
        User user = (User) session.getAttribute("loggedInUser");

        // Card Image
        Card_image cardImage = imgService.getByName(image);
        if (cardImage == null && imageFile != null && !imageFile.isEmpty()) {
            cardImage = new Card_image();
            cardImage.setName(image);
            cardImage.setImage(imageFile.getBytes());
            cardImage.setContent_type(imageFile.getContentType());
            imgService.insert(cardImage);
        }

        // Create and insert the new card
        Card card = new Card();
        card.setName(name);
        card.setDescription(desc);
        card.setAttack(atk);
        card.setDefense(def);
        card.setLevel(lvl);
        card.setArchetype(archetype);
        card.setCard_type(cardType);
        card.setAttribute(attrr);
        card.setFrame_type(frameType);
        card.setRace(cardRace);
        card.setImage(cardImage);
        card.setUser(user);

        cardService.insert(card);

        return "card";
    }

}

