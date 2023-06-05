package com.ipi.jva320.controller;

import java.time.LocalDate;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;

@Controller
public class HomeController {
	
	@Autowired
	SalarieAideADomicileService salarieAideADomicileService;
	
	@GetMapping(value = "/")
	public String home(final ModelMap model) {
		model.put("message", "Hello World !");
		model.put("nb_salaries", salarieAideADomicileService.countSalaries() + " salariés");
		
        try {
			SalarieAideADomicile s1 = this.salarieAideADomicileService.creerSalarieAideADomicile(
			        new SalarieAideADomicile("Jean", LocalDate.parse("2022-12-05"), LocalDate.parse("2022-12-05"),
			                20, 0,
			                80, 10, 1));
		} catch (EntityExistsException e) {
			e.printStackTrace();
		} catch (SalarieException e) {
			e.printStackTrace();
		}
		
		return "home";
	}
	
	@GetMapping(value = "/salaries/{id}")
	public String detail_Salarie(final ModelMap model, @PathVariable Long id) {
		model.put("salarie", salarieAideADomicileService.getSalarie(id));
		return "detail_Salarie";
	}
	
}
