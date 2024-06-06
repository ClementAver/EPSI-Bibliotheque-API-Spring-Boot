package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
import com.example.tpsrpingbibliotheque.repositories.LivreRepository;
import com.example.tpsrpingbibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LivreController {

    // @Autowired if no constructor.
    final private LivreService livreService;
    private final LivreRepository livreRepository;

    public LivreController(LivreService livreService, LivreRepository livreRepository) {
        this.livreService = livreService;
        this.livreRepository = livreRepository;
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

    @PutMapping("/emprunt/{id}")
    public void empruntLivre(@RequestBody LivreRequestBody livreRequestBody, @PathVariable int id, @RequestParam(name="emprunt", required = true) boolean emprunt) {
        try {
            livreService.empruntLivre(id, emprunt, livreRequestBody);
        } catch (LivreNonDisponibleExeption e) {
            System.out.println(e.getMessage());
        }
    }
}
