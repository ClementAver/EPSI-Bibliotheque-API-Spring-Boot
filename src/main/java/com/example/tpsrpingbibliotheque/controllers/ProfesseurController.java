package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.ProfesseurRequestBody;
import com.example.tpsrpingbibliotheque.entities.Professeur;
import com.example.tpsrpingbibliotheque.services.ProfesseurService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProfesseurController {

    // @Autowired if no constructor.
    final private ProfesseurService professeurService;

    public ProfesseurController(ProfesseurService professeurService) {
        this.professeurService = professeurService;
    }

    @GetMapping("/professeurs")
    public List<Professeur> getProfesseurs() {
        return professeurService.getAllProfesseurs();
    }

    @GetMapping("/professeur")
    public Professeur getProfesseur(@RequestParam(name = "id", required=true) int id) {
        return professeurService.getProfesseur(id);
    }

    @PostMapping("/professeur")
    public Professeur createProfesseur(@RequestBody ProfesseurRequestBody professeurRequestBody) {
        return professeurService.createProfesseur(professeurRequestBody);
    }

    @DeleteMapping("/professeur")
    public void deleteProfesseur(@RequestParam(name = "id", required=true) int id) {
        professeurService.deleteProfesseur(id);
    }
}
