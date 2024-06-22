package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.MembreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.services.MembreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MembreController {

    // @Autowired if no constructor.
    final private MembreService
            membreService;

    public MembreController(MembreService membreService) {
        this.membreService = membreService;
    }

    @GetMapping("/membres")
    public List<Membre> getMembres() {
        return membreService.getAllMembres();
    }

    @GetMapping("/membre")
    public Membre getMembre(@RequestParam(name = "id", required=true) int id) {
        return membreService.getMembre(id);
    }

    @PostMapping("/membre")
    public Membre createMembre(@RequestBody MembreRequestBody membreRequestBody) {
        return membreService.createMembre(membreRequestBody);
    }

    @DeleteMapping("/membre")
    public void deleteMembre(@RequestParam(name = "id", required=true) int id) {
        membreService.deleteMembre(id);
    }
}
