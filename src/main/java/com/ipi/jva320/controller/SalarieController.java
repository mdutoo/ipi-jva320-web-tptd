package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/salaries/{id}")
    public String salariesDetails(@PathVariable(value = "id") String id, final ModelMap model) throws SalarieException {
        SalarieAideADomicile salarieAideADomicileJeannette = new SalarieAideADomicile("Jeannette Dupontelle", LocalDate.of(2021, 7, 1), LocalDate.now(), 0, 0, 10, 1, 0);
        salarieAideADomicileService.creerSalarieAideADomicile(salarieAideADomicileJeannette);

        SalarieAideADomicile salarieDetails = salarieAideADomicileService.getSalarie(Long.valueOf(id));
        model.put("salarieDetails", salarieDetails);
        return "detail_Salarie";
    }

    @PostMapping("/salaries/{id}")
    public String updateSalarie(SalarieAideADomicile salarieDetails) throws SalarieException {
        salarieAideADomicileService.updateSalarieAideADomicile(salarieDetails);
        return "redirect:/salaries/" + salarieDetails.getId();
    }

    @PostMapping("/salaries/aide/new")
    public String createSalarie(final ModelMap model){
        model.put("salarieDetails", null );
        return "detail_Salarie";
    }
}
