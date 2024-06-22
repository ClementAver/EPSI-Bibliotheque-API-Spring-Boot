package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.LivreDTO;
import com.example.tpsrpingbibliotheque.repositories.LivreRepository;
import com.example.tpsrpingbibliotheque.services.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class LivreController {

    // @Autowired if no constructor.
    final private LivreService livreService;

    public LivreController(LivreService livreService, LivreRepository livreRepository) {
        this.livreService = livreService;
    }

    @GetMapping("/livres")
    public Stream<LivreDTO> getLivres() {
        return livreService.getAllLivres();
    }

    @GetMapping("/livre")
    public LivreDTO getLivre(@RequestParam(name = "id", required=true) Long id) {
        return livreService.getLivre(id);
    }

    @PostMapping("/livre")
    public void createLivre(@RequestBody LivreDTO livreDTO) {
        livreService.createLivre(livreDTO);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam(name = "id", required=true) Long id) {
        livreService.deleteLivre(id);
    }
}