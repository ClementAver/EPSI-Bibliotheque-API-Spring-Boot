package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LivreController {

    // @Autowired if no constructor.
    final private LivreService livreService;

    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping("/livres")
    public List<Livre> getLivres() {
        return livreService.getAllLivres();
    }

    @GetMapping("/livre")
    public Livre getLivre(@RequestParam(name = "id", required=true) int id) {
        return livreService.getLivre(id);
    }

    @PostMapping("/livre")
    public Livre createLivre(@RequestBody LivreRequestBody livreRequestBody) {
        return livreService.createLivre(livreRequestBody);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam(name = "id", required=true) int id) {
        livreService.deleteLivre(id);
    }
}
