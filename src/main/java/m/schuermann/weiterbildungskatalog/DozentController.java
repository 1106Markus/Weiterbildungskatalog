package m.schuermann.weiterbildungskatalog;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DozentController {
	@Autowired
	private DozentRepository dozentRepository;
	
	private final WeiterbildungsangebotRepository weiterbildungsangebotRepository;
    public DozentController(WeiterbildungsangebotRepository weiterbildungsangebotRepository) {
        this.weiterbildungsangebotRepository = weiterbildungsangebotRepository;
    }
	
	//Mapping zur Homepage
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	//Dozent*innen hinzufügen
	@GetMapping("/dozentTemplates/newDozentForm")
	public String createDozentForm(Model m) {
		m.addAttribute("dozent", new Dozent());
		return "dozentTemplates/newDozentForm";
	}
	
	@PostMapping("/dozentTemplates/dozentSpeichern")
    public String saveDozent(@ModelAttribute("dozent") Dozent dozent) {
        dozentRepository.save(dozent);
        return "redirect:/dozentTemplates/showDozenten";
    }
	
	//Anzeigen aller Dozenti*innen
	@GetMapping("/dozentTemplates/showDozenten")
	public String showAllDozenten(Model m) {
		List<Dozent> dozenten = dozentRepository.findAll();
		m.addAttribute("dozenten", dozenten);
		return "dozentTemplates/showDozenten";
	}
	
	//Dozent*in entfernen
	@GetMapping("/dozentTemplates/deleteDozent")
	public String deleteDozentPage(Model m) {
		List<Dozent> dozenten = dozentRepository.findAll();
		m.addAttribute("dozentList", dozenten);
		return "dozentTemplates/deleteDozent";
	}
	
	@PostMapping("/dozentTemplates/deleteDozent")
	public String deleteDozent(@RequestParam("dozentID") Long id) {
	    dozentRepository.deleteById(id);
	    return "redirect:/dozentTemplates/showDozenten";
	}
	
	//Dozent*in updaten
	@GetMapping("/dozentTemplates/updateDozentAuswahl")
	public String updateDozentAuswahl(Model m) {
		List<Dozent> dozenten = dozentRepository.findAll();
		m.addAttribute("dozentList", dozenten);
		return "dozentTemplates/updateDozentAuswahl";
	}
	
	@GetMapping("/dozentTemplates/updateDozentForm")
	public String updateDozentForm(@RequestParam(name="dozentID", required=false) Long dozentID, Model m) {
	    List<Dozent> dozenten = dozentRepository.findAll();
	    m.addAttribute("dozentList", dozenten);
	    m.addAttribute("dozent", dozentRepository.findById(dozentID).orElse(null));
	    return "dozentTemplates/updateDozentForm";
	}
	
	@PostMapping("/dozentTemplates/updateDozentForm/{dozentID}")
	public String updateDozent(@PathVariable Long dozentID, @ModelAttribute("dozent") Dozent dozent, BindingResult result, @RequestParam(value = "weiterbildungsangebote", required = false) Set<Long> angebotIDs) {
	    if (result.hasErrors()) {
	        return "dozentTemplates/updateDozentForm";
	    }
	    Dozent updatedDozent = dozentRepository.findById(dozentID)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid dozent Id:" + dozentID));
	    
	    if (angebotIDs != null) {
	        Set<Weiterbildungsangebot> weiterbildungsangebote = (Set<Weiterbildungsangebot>) weiterbildungsangebotRepository.findAllById(angebotIDs);
	        updatedDozent.setWeiterbildungsangebote(weiterbildungsangebote);
	    }
	    
	    
	    updatedDozent.setVorname(dozent.getVorname());
	    updatedDozent.setNachname(dozent.getNachname());
	    
	    dozentRepository.save(updatedDozent);
	    
	    return "redirect:/dozentTemplates/showDozenten";
	}
}

