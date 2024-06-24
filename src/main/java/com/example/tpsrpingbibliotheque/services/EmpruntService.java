package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.entities.Emprunt;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.mappers.EmpruntDTOMapper;
import com.example.tpsrpingbibliotheque.repositories.EmpruntRepository;
import com.example.tpsrpingbibliotheque.repositories.LivreRepository;
import com.example.tpsrpingbibliotheque.repositories.MembreRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class EmpruntService implements EmpruntInterface {

    private final EmpruntRepository empruntRepository;
    private final EmpruntDTOMapper empruntDTOMapper;
    private final LivreRepository livreRepository;
    private final MembreRepository membreRepository;

    public EmpruntService(EmpruntRepository empruntRepository, LivreRepository livreRepository, MembreRepository membreRepository) {
        this.empruntRepository = empruntRepository;
        this.empruntDTOMapper = new EmpruntDTOMapper();
        this.livreRepository = livreRepository;
        this.membreRepository = membreRepository;
    }

    @Override
    public Stream<EmpruntDTO> getEmprunts() {
        return empruntRepository.findAll()
                .stream().map(empruntDTOMapper);
    }

    @Override
    public EmpruntDTO getEmprunt(Long id) {
        Optional<Emprunt> empruntInDB = empruntRepository.findById(id);
        if (empruntInDB.isPresent()) {
            Emprunt emprunt = empruntInDB.get();
            return new EmpruntDTO(emprunt.getId(), emprunt.getLivre().getId(), emprunt.getMembre().getId(), emprunt.getDateEmprunt());
        }
        return null;
    }

    @Override
    public void createEmprunt(Membre membre, Livre livre) {
        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(livre);
        emprunt.setMembre(membre);
        emprunt.setDateEmprunt(LocalDate.now());
        empruntRepository.save(emprunt);
    }

    @Override
    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }
}
