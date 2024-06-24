package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.LivreDTO;
import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
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

    @GetMapping("/livres/{id}")
    public LivreDTO getLivre(@PathVariable Long id) {
        return livreService.getLivre(id);
    }

    @PostMapping("/livres")
    public void createLivre(@RequestBody LivreDTO livreDTO) {
        livreService.createLivre(livreDTO);
    }

    @DeleteMapping("/livres/{id}")
    public void deleteUser(@PathVariable Long id) {
        livreService.deleteLivre(id);
    }

    @PostMapping("livres/emprunter")
    public void emprunter(@RequestBody EmpruntDTO empruntDTO) throws LivreNonDisponibleExeption {
        livreService.emprunter(empruntDTO);
    }

    @PostMapping("livres/rendre")
    public String rendre(@RequestBody EmpruntDTO empruntDTO) throws LivreNonDisponibleExeption {
        return livreService.rendre(empruntDTO);
    }

    @PostMapping("livres/reserver")
    public void reserver(@RequestBody ReservationDTO reservationDTO) throws LivreNonDisponibleExeption {
        livreService.reserver(reservationDTO);
    }
}