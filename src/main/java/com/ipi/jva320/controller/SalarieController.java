package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        model.put("titreLienNavBar", "Accueil");
        model.put("nombreSalaries", salarieAideADomicileService.countSalaries());
        return "detail_Salarie";
    }

    @GetMapping("/salaries")
    public String listSalaries(final ModelMap model, @RequestParam(value = "nom", required = false, defaultValue = "tousLesSalaries") String paramNom,
                               @RequestParam(value = "page", defaultValue = "0" ) String paramPage, @RequestParam(value = "size", defaultValue = "10") String paramSize) {
       if (paramNom.equals("tousLesSalaries")) {
           Page<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries(PageRequest.of(Integer.parseInt(paramPage), Integer.parseInt(paramSize), Sort.by("nom").ascending()));
           model.put("salaries", salaries);
        } else {
           List<SalarieAideADomicile> salaries = salarieAideADomicileService.getSalaries(paramNom, PageRequest.of(Integer.parseInt(paramPage), Integer.parseInt(paramSize), Sort.by("nom").ascending()));
           model.put("salaries", salaries);
       }
        model.put("page", paramPage);
        model.put("pagePremierSalarie", (Integer.parseInt(paramPage)*10)+1);
        model.put("pageDernierSalarie", Math.min((Integer.parseInt(paramPage)* 10L)+10, salarieAideADomicileService.countSalaries()));
        model.put("size", paramSize);
        model.put("sortDirection", "ASC");
        model.put("titrePage", "Liste des salariés");
        model.put("titreLienNavBar", "Accueil");
        model.put("nombreSalaries", salarieAideADomicileService.countSalaries());
        return "list";
    }

    @PostMapping("/salaries/{id}")
    public String updateSalarie(@Valid SalarieAideADomicile salarieDetails, BindingResult bindingResult) throws SalarieException {
        if (bindingResult.hasErrors()) {
            System.out.println("Problème de désérialisation");
        }
        salarieAideADomicileService.updateSalarieAideADomicile(salarieDetails);
        return "redirect:/salaries/" + salarieDetails.getId();
    }

    @GetMapping("/salaries/aide/new")
    public String newSalarie(final ModelMap model){
        model.put("titrePage", "Nouveau salarié");
        model.put("titreLienNavBar", "Accueil");
        model.put("nombreSalaries", salarieAideADomicileService.countSalaries());
        return "new_Salarie";
    }

    @PostMapping("/salaries/aide/new")
    public String createSalarie(@Valid SalarieAideADomicile salarieDetails, BindingResult bindingResult) throws SalarieException {
        if (bindingResult.hasErrors()) {
            System.out.println("Problème de désérialisation");
        }
        salarieAideADomicileService.creerSalarieAideADomicile(salarieDetails);
        return "redirect:/salaries/" + salarieDetails.getId();
    }

    @GetMapping("/salaries/{id}/delete")
    public String deleteSalarie(@PathVariable(value = "id") String id, final ModelMap model) throws SalarieException {
        salarieAideADomicileService.deleteSalarieAideADomicile(Long.valueOf(id));
        return "redirect:/salaries/";
    }
}
