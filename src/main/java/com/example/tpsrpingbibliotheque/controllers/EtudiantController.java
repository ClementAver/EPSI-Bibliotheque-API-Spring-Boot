package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.EtudiantRequestBody;
import com.example.tpsrpingbibliotheque.entities.Etudiant;
import com.example.tpsrpingbibliotheque.services.EtudiantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EtudiantController {

    // @Autowired if no constructor.
    final private EtudiantService etudiantService;

    public EtudiantController(EtudiantService etudiantService) {
        this.etudiantService = etudiantService;
    }

    @GetMapping("/etudiants")
    public List<Etudiant> getEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    @GetMapping("/etudiant")
    public Etudiant getEtudiant(@RequestParam(name = "id", required=true) int id) {
        return etudiantService.getEtudiant(id);
    }

    @PostMapping("/etudiant")
    public Etudiant createEtudiant(@RequestBody EtudiantRequestBody etudiantRequestBody) {
        return etudiantService.createEtudiant(etudiantRequestBody);
    }

    @DeleteMapping("/etudiant")
    public void deleteEtudiant(@RequestParam(name = "id", required=true) int id) {
        etudiantService.deleteEtudiant(id);
    }
}
