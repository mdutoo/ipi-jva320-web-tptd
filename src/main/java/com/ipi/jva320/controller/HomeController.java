package com.ipi.jva320.controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Controller
public class HomeController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/")
    public String home(final ModelMap model) {
        //model.put("message", "Bienvenue dans l'interface d'administration RH ! ( " + salarieAideADomicileService.countSalaries() + " salariés)");
        model.put("nombreSalaries", salarieAideADomicileService.countSalaries());
        model.put("titrePage", "Aide à domicile RH - gestion des salariés");
        return "home";
    }

}
