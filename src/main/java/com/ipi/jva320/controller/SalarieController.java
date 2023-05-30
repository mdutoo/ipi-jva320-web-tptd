package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/salaries/{id}")
    public String salariesDetails(@PathVariable(value = "id") String id, final ModelMap model) throws SalarieException {
        SalarieAideADomicile salarieDetails = salarieAideADomicileService.getSalarie(Long.valueOf(id));
        model.put("salarieDetails", salarieDetails);
        model.put("titrePage", "Details du salarié");
        return "detail_Salarie";
    }

    @GetMapping("/salaries")
    public String listSalaries(final ModelMap model, @RequestParam(value = "nom", required = false, defaultValue = "tousLesSalaries") String paramNom) {
        List<SalarieAideADomicile> salaries;
       if (paramNom.equals("tousLesSalaries")) {
           salaries = salarieAideADomicileService.getSalaries();
        } else {
           salaries = salarieAideADomicileService.getSalaries(paramNom);
        }
        model.put("salaries", salaries);
        model.put("titrePage", "Liste des salariés");
        return "list";
    }

    @PostMapping("/salaries/{id}")
    public String updateSalarie(SalarieAideADomicile salarieDetails) throws SalarieException {
        salarieAideADomicileService.updateSalarieAideADomicile(salarieDetails);
        return "redirect:/salaries/" + salarieDetails.getId();
    }

    @GetMapping("/salaries/aide/new")
    public String newSalarie(final ModelMap model){
        return "new_Salarie";
    }

    @PostMapping("/salaries/aide/new")
    public String createSalarie(SalarieAideADomicile salarieDetails) throws SalarieException {
        salarieAideADomicileService.creerSalarieAideADomicile(salarieDetails);
        return "redirect:/salaries/" + salarieDetails.getId();
    }

    @GetMapping("/salaries/{id}/delete")
    public String deleteSalarie(@PathVariable(value = "id") String id, final ModelMap model) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(Long.valueOf(id));
        return "redirect:/salaries/";
    }
}
