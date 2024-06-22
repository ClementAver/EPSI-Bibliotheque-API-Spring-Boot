package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.entities.Emprunt;
import com.example.tpsrpingbibliotheque.mappers.EmpruntDTOMapper;
import com.example.tpsrpingbibliotheque.repositories.EmpruntRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class EmpruntService implements EmpruntInterface {

    private final EmpruntRepository empruntRepository;
    private final EmpruntDTOMapper empruntDTOMapper;

    public EmpruntService(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
        this.empruntDTOMapper = new EmpruntDTOMapper();
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
            return new EmpruntDTO(emprunt.getId(), emprunt.getLivre(), emprunt.getMembre(), emprunt.getDateEmprunt());
        }
        return null;
    }

    @Override
    public void createEmprunt(EmpruntDTO empruntDTO) {
        Emprunt emprunt = new Emprunt();
        emprunt.setLivre(empruntDTO.getLivre());
        emprunt.setMembre(empruntDTO.getMembre());
        emprunt.setDateEmprunt(LocalDate.now());
        empruntRepository.save(emprunt);
    }

    @Override
    public void deleteEmprunt(Long id) {
        empruntRepository.deleteById(id);
    }
}
