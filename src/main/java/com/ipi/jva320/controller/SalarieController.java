package com.ipi.jva320.controller;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;

@Controller
public class SalarieController {

	@Autowired
	SalarieAideADomicileService salarieAideADomicileService;
	
	@GetMapping(value = "/salaries/aide/new")
	public String new_salarie(final ModelMap model) {
		return "new_Salarie";
	}
	
	@PostMapping(value = "/salaries/save")
	public String create_salarie(SalarieAideADomicile salarie) throws EntityExistsException, SalarieException {
		salarieAideADomicileService.creerSalarieAideADomicile(salarie);
		return "redirect:/salaries/" + salarie.getId();
	}
	
	@GetMapping(value = "/salaries")
	public String all_salaries(final ModelMap model) {
		model.put("salaries", salarieAideADomicileService.getSalaries());
		//System.out.println(salarieAideADomicileService.getSalaries());
		return "list";
	}
	
	@PostMapping(value = "/salaries/{id}")
	public String save_salarie(SalarieAideADomicile salarie, @PathVariable Long id) throws EntityExistsException, SalarieException {
		salarieAideADomicileService.updateSalarieAideADomicile(salarie);
		return "redirect:/salaries/" + id;
	}
	
	@GetMapping(value = "/salaries/delete/{id}")
	public String delete_salarie(SalarieAideADomicile salarie, @PathVariable Long id) throws EntityExistsException, SalarieException {
		salarieAideADomicileService.deleteSalarieAideADomicile(id);
		return "redirect:/salaries";
	}
	
	@RequestMapping(value = "/salaries/findbynom", method = RequestMethod.GET)
	public String find_salarie_by_name(SalarieAideADomicile salarieAideADomicile, BindingResult bindingResult, final ModelMap model, @RequestParam("nom") String nom) {
		List<SalarieAideADomicile> salarie = salarieAideADomicileService.getSalaries(nom);
		System.out.println(salarie);
		if(salarie.size() == 0) {
			model.put("error", "Erreur !");
			System.out.println("Pas trouvé");
			return "redirect:/";
		}
		//S'il y a plusieurs personnes avec le même nom, j'affiche le premier de la liste
	    Long id = salarie.get(0).getId();
	    return "redirect:/salaries/" + id;
	}
	
	
}
