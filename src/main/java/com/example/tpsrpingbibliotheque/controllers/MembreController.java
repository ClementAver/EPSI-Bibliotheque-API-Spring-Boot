package com.example.tpsrpingbibliotheque.controllers;

import com.example.tpsrpingbibliotheque.dto.MembreDTO;
import com.example.tpsrpingbibliotheque.dto.MembreRequestBody;
import com.example.tpsrpingbibliotheque.services.MembreService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@RestController
@RequestMapping("/api")
public class MembreController {

    // @Autowired if no constructor.
    final private MembreService membreService;

    public MembreController(MembreService membreService) {
        this.membreService = membreService;
    }

    @GetMapping("/membres")
    public Stream<MembreDTO> getMembres() {
        return membreService.getAllMembres();
    }

    @GetMapping("/membre")
    public MembreDTO getMembre(@RequestParam(name = "id", required=true) Long id) {
        return membreService.getMembre(id);
    }

    @PostMapping("/membre")
    public void createMembre(@RequestBody MembreRequestBody membreRequestBody) {
        membreService.createMembre(membreRequestBody);
    }

    @DeleteMapping("/membre")
    public void deleteMembre(@RequestParam(name = "id", required=true) Long id) {
        membreService.deleteMembre(id);
    }
}
