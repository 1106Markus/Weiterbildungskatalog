package m.schuermann.weiterbildungskatalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeiterbildungsangebotController {
	@Autowired
	private WeiterbildungsangebotRepository weiterbildungsangebotRepository;
	
	private final DozentRepository dozentRepository;
    public WeiterbildungsangebotController(DozentRepository dozentRepository) {
        this.dozentRepository = dozentRepository;
    }
	
    // Weiterbildungsangebot hinzufügen
	@GetMapping("/weiterbildungsangebotTemplates/addWeiterbildungsangebotForm")
	public String addWeiterbildungsangebotForm(Model m) {
	    m.addAttribute("weiterbildungsangebot", new Weiterbildungsangebot());
	    m.addAttribute("dozentList", dozentRepository.findAll());
	    return "weiterbildungsangebotTemplates/addWeiterbildungsangebotForm";
	}

	@PostMapping("/weiterbildungsangebotTemplates/addWeiterbildungsangebot")
	public String addWeiterbildungsangebot(@ModelAttribute("weiterbildungsangebot") Weiterbildungsangebot weiterbildungsangebot, BindingResult result) {
	    if (result.hasErrors()) {
	        return "weiterbildungsangebotTemplates/addWeiterbildungsangebotForm";
	    }
	    
	    weiterbildungsangebotRepository.save(weiterbildungsangebot);
	    dozentRepository.findById(weiterbildungsangebot.getDozent().getDozentID()).ifPresent(dozent -> {
	        dozent.getWeiterbildungsangebote().add(weiterbildungsangebot);
	        dozentRepository.save(dozent); 
	    });

	    return "redirect:/weiterbildungsangebotTemplates/showWeiterbildungsangebote";
	}
	
	// Weiterbildungsangebote anzeigen
	@GetMapping("/weiterbildungsangebotTemplates/showWeiterbildungsangebote")
    public String showWeiterbildungsangebote(Model model) {
		List<Weiterbildungsangebot> weiterbildungsangebote = weiterbildungsangebotRepository.findAll();
        model.addAttribute("weiterbildungsangebote", weiterbildungsangebote);
        return "weiterbildungsangebotTemplates/showWeiterbildungsangebote";
    }
	
	//Weiterbildungsangebot löschen
	@GetMapping("/weiterbildungsangebotTemplates/deleteWeiterbildungsangebot")
	public String deleteWeiterbildungsangebotPage(Model m) {
		List<Weiterbildungsangebot> weiterbildungsangebote = weiterbildungsangebotRepository.findAll();
		m.addAttribute("weiterbildungsangebote", weiterbildungsangebote);
		return "weiterbildungsangebotTemplates/deleteWeiterbildungsangebot";
	}
	
	@PostMapping("/weiterbildungsangebotTemplates/deleteWeiterbildungsangebot")
	public String deleteWeiterbildungsangebot(@RequestParam("angebotID") Long id) {
		weiterbildungsangebotRepository.deleteById(id);
		return "redirect:/weiterbildungsangebotTemplates/showWeiterbildungsangebote";
	}
	
	//Weiterbildungsangebot updaten
	@GetMapping("/weiterbildungsangebotTemplates/updateWeiterbildungsangebotAuswahl")
	public String updateWeiterbildungsangebotAuswahl(Model m) {
		List<Weiterbildungsangebot> weiterbildungsangebote = weiterbildungsangebotRepository.findAll();
		m.addAttribute("weiterbildungsangebote", weiterbildungsangebote);
		return "weiterbildungsangebotTemplates/updateWeiterbildungsangebotAuswahl";
	}
	
	@GetMapping("/weiterbildungsangebotTemplates/updateWeiterbildungsangebotForm")
	public String updateWeiterbildungsangebotForm(@RequestParam(name="angebotID", required=false) Long angebotID, Model m) {
	    m.addAttribute("weiterbildungsangebot", weiterbildungsangebotRepository.findById(angebotID).orElse(null));
	    m.addAttribute("dozentList", dozentRepository.findAll());
	    return "weiterbildungsangebotTemplates/updateWeiterbildungsangebotForm";
	}
	
	@PostMapping("/weiterbildungsangebotTemplates/updateWeiterbildungsangebotForm/{angebotID}")
	public String updateDozent(@ModelAttribute("weiterbildungsangebot") Weiterbildungsangebot weiterbildungsangebot, BindingResult result) {
	    if (result.hasErrors()) {
	        return "weiterbildungsangebotTemplates/updateWeiterbildungsangebotForm";
	    }

	    weiterbildungsangebotRepository.save(weiterbildungsangebot);
	    return "redirect:/weiterbildungsangebotTemplates/showWeiterbildungsangebote";
	}
}
