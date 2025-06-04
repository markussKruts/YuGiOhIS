package jtt.test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jtt.test.dao.Card_imageDAO;
import jtt.test.dao.impl.ArchetypeDAOImpl;
import jtt.test.dao.impl.AttributeDAOImpl;
import jtt.test.dao.impl.CardDAOImpl;
import jtt.test.dao.impl.Card_imageDAOImpl;
import jtt.test.dao.impl.Card_setDAOImpl;
import jtt.test.dao.impl.Card_typeDAOImpl;
import jtt.test.dao.impl.DeckDAOImpl;
import jtt.test.dao.impl.Deck_CardsDAOImpl;
import jtt.test.dao.impl.Frame_typeDAOImpl;
import jtt.test.dao.impl.RaceDAOImpl;
import jtt.test.dao.impl.RaritiesDAOImpl;
import jtt.test.dao.impl.SetDAOImpl;
import jtt.test.dao.impl.UserDAOImpl;
import jtt.test.dto.Archetype;
import jtt.test.dto.Attribute;
import jtt.test.dto.Card;
import jtt.test.dto.Card_Type;
import jtt.test.dto.Card_image;
import jtt.test.dto.Card_set;
import jtt.test.dto.Deck;
import jtt.test.dto.Deck_Cards;
import jtt.test.dto.FrameType;
import jtt.test.dto.Race;
import jtt.test.dto.Rarities;
import jtt.test.dto.Set;
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
	SetDAOImpl setService;
	@Autowired
	RaritiesDAOImpl rareService;
	@Autowired
	Card_setDAOImpl cardSetService;
	@Autowired
	DeckDAOImpl deckService;
	
	@Autowired
	Deck_CardsDAOImpl deckCardService;
	
    @Autowired
    private PasswordEncoder passwordEncoder;
	
    @GetMapping("/signup")
	public String sign(Model model) {
	model.addAttribute("message", "signup");
	return "signup";
    }
    @GetMapping("/proxy-random")
    public ResponseEntity<?> getRandomCard() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://db.ygoprodeck.com/api/v7/randomcard.php";

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response.getBody());
            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Failed to fetch card.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
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
    			model.addAttribute("error", "Incorrect password.");
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
    @GetMapping("/set")
	public String set(Model model) {
	model.addAttribute("message", "signup");
	return "set";
    }
    @GetMapping("/deck")
   	public String deck(Model model) {
   	model.addAttribute("message", "signup");
   	return "deck";
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
    @GetMapping("/ownSets")
    public String getSets(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        List<Set> sets = setService.getByUser(user);
        System.out.println("Sets found: " + sets.size()); // Debug
        for (Set c : sets) {
            System.out.println("Set: " + c.getName());
        }
        model.addAttribute("sets", sets);
        
        return "ownSets";
    }
    
    @GetMapping("/ownDecks")
    public String getDecks(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        List<Deck> decks = deckService.getByUser(user);
        System.out.println("Decks found: " + decks.size()); // Debug
        for (Deck c : decks) {
            System.out.println("Deck: " + c.getName());
        }
        model.addAttribute("decks", decks);
        
        return "ownDecks";
    }
    
    
    @GetMapping("/allCards")
    public String getAllCards(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        
        return "allCards";
    }
    @GetMapping("/allSets")
    public String getAllSets(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        
        return "allSets";
    }
    @GetMapping("/smash")
    public String smash(Model model, HttpSession session) throws SQLException {
    	User user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("user", user);
        
        return "smash";
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
    @GetMapping("/card/list")
    @ResponseBody
    public List<Map<String, Object>> listCard() {
        return cardService.getAllData().stream().map(typee -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", typee.getId());
            map.put("name", typee.getName());
            return map;
        }).toList();
    }

    @PostMapping("/deleteCard/{id}")
    public ResponseEntity<Void> deletCard(@PathVariable int id) {
        Card card = cardService.getByID(id);
        if (card == null) return ResponseEntity.notFound().build();
        
        cardService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/deleteSet/{id}")
    public ResponseEntity<Void> deletSet(@PathVariable int id) {
        Set set = setService.getByID(id);
        if (set == null) return ResponseEntity.notFound().build();
        
        setService.delete(id);
        
        return ResponseEntity.noContent().build();
    }
	@GetMapping("/setbuilder")
	public String setBuild(Model model, @RequestParam(required = false) Integer id) {
	    if (id != null) {
	        Set set = setService.getByID(id);
	        model.addAttribute("set", set);
	        System.err.println(set.getName());
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            String setJson = objectMapper.writeValueAsString(set);
	            System.err.println(setJson);
	            model.addAttribute("setJson", setJson);
	        } catch (Exception e) {
	            model.addAttribute("setJson", "{}");
	        }

	    } else {
	    	Set set = new Set();
	        model.addAttribute("set", set);
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            String setJson = objectMapper.writeValueAsString(set);
	            System.err.println(setJson);
	            model.addAttribute("setJson", setJson);
	        } catch (Exception e) {
	            model.addAttribute("setJson", "{}");
	        }

	    }
	return "setBuilder";
	}
	
	
	@GetMapping("/customdeck")
	public String deckbuild(Model model, @RequestParam(required = false) Integer id) {
	    if (id != null) {
	        Deck deck = deckService.getByID(id);
	        model.addAttribute("card", deck);
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            String cardJson = objectMapper.writeValueAsString(deck);
	            model.addAttribute("cardJson", cardJson);
	        } catch (Exception e) {
	            model.addAttribute("cardJson", "{}");
	        }

	    } else {
	    	Deck deck = new Deck();
	        model.addAttribute("deck", deck);
	        try {
	            ObjectMapper objectMapper = new ObjectMapper();
	            String cardJson = objectMapper.writeValueAsString(deck);
	            model.addAttribute("deckJson", cardJson);
	        } catch (Exception e) {
	            model.addAttribute("deckJson", "{}");
	        }

	    }
	return "customdeck";
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
	@PostMapping("/submit-set")
	public String submitSet(
	        @RequestParam("name") String name,
	        @RequestParam("set_code") String setCode,
	        @RequestParam(value = "cards_data") String cardsDataJson,
	        @RequestParam(value = "image-image", required = false) MultipartFile imageFile,
	        @RequestParam(value = "image_select", required = false) Integer imageId,
	        @RequestParam(value = "image", required = false) String imageName,
	        @RequestParam(required = false) Integer id,
	        HttpSession session
	) throws SQLException, IOException {
		Set set;
		if (id != null && id != 0) {
		    set = setService.getByID(id);
		    if (set == null) {
		        // Fallback if the card doesn't exist — prevent insert
		        return "redirect:/card?error=CardNotFound";
		    }
		} else {
		    set = new Set();
		}
		
	    set.setName(name);
	    set.setCode(setCode);
	    User userr = (User) session.getAttribute("loggedInUser");
	    if (userr == null) {
	        return "redirect:/login";
	    }
	    // Handle image
	    Card_image cardImage = null;

	    if (imageFile != null && !imageFile.isEmpty()) {
	        cardImage = new Card_image();
	        cardImage.setName(imageName);
	        cardImage.setImage(imageFile.getBytes());
	        cardImage.setContent_type(imageFile.getContentType());
	        imgService.insert(cardImage);
	    } else if (imageId != null) {
	        cardImage = imgService.getByID(imageId);
	    } else if (imageName != null && !imageName.isBlank()) {
	        cardImage = imgService.getByName(imageName);
	    }

	    set.setImage(cardImage);
	    // Handle User
	    User user = service.getByID(userr.getUser_id());
	    set.setUser(user);
	    // Parse cardsDataJson
	    List<Card_set> cardSets = new ArrayList<>();
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        List<Map<String, String>> cardsData = objectMapper.readValue(cardsDataJson, new TypeReference<>() {
	        });

	        for (Map<String, String> entry : cardsData) {
	            int cardId = Integer.parseInt(entry.get("id"));
	            String rarityName = entry.get("rarity");

	            Card card = cardService.getByID(cardId);
	            Rarities rarity = rareService.getByName(rarityName);

	            Card_set cardSet = new Card_set();
	            cardSet.setCard_id(card);
	            cardSet.setSet_id(set);
	            cardSet.setRarity_id(rarity);

	            cardSets.add(cardSet);
	        }
	    } catch (Exception e) {
	        throw new IllegalArgumentException("Invalid card data format", e);
	    }

	    if (id != null && id != 0) {
	        setService.update(set, id);
	        
	        cardSetService.deleteBySetId(id);
	        
	        for (Card_set cs : cardSets) {
	            cardSetService.insert(cs);
	        }
	    } else {
	        setService.insert(set);
	        
	        for (Card_set cs : cardSets) {
	            cardSetService.insert(cs);
	        }
	    }
	    
	    return "redirect:/set";
	}
	@PostMapping("/create")
    public ResponseEntity<String> createDeck(@RequestBody Map<String, Object> payload, HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to save a deck.");
        }

        String name = (String) payload.get("name");
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().body("Deck name is required.");
        }

        List<Integer> cardIds;
        try {
            cardIds = ((List<?>) payload.get("cards")).stream()
                .map(Object::toString)
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid card list.");
        }

        try {
            // Save deck
            Deck deck = new Deck();
            deck.setName(name);
            deck.setUser(user);
            deckService.insert(deck);  // Should auto-set ID if JPA is correct

            // Save each card in deck
            for (Integer cardId : cardIds) {
                Card card = cardService.getByID(cardId);
                if (card != null) {
                    Deck_Cards deckCard = new Deck_Cards();
                    deckCard.setDeck(deck);
                    deckCard.setCard(card);
                    deckCardService.insert(deckCard);
                }
            }

            return ResponseEntity.ok("Deck saved successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save deck.");
        }
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

