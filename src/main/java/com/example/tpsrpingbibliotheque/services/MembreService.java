package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.MembreDTO;
import com.example.tpsrpingbibliotheque.dto.MembreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Etudiant;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.entities.Professeur;
import com.example.tpsrpingbibliotheque.mappers.MembreDTOMapper;
import com.example.tpsrpingbibliotheque.repositories.MembreRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MembreService implements MembreInterface {

    private final MembreRepository membreRepository;
    private final MembreDTOMapper membreDTOMapper;

    public MembreService(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
        this.membreDTOMapper = new MembreDTOMapper();
    }

    @Override
    public Stream<MembreDTO> getAllMembres() {
        return membreRepository.findAll()
                .stream().map(membreDTOMapper);
    }

    @Override
    public MembreDTO getMembre(Long id) {
        Optional<Membre> membreInDB = membreRepository.findById(id);
        if (membreInDB.isPresent()) {
            Membre membre = membreInDB.get();
            return new MembreDTO(membre.getId(), membre.getNom(), membre.getPrenom());
        }
        return null;
    }

    @Override
    public void createMembre(MembreRequestBody membreRequestBody) {
        Membre membre;
        if (membreRequestBody.getNiveau() != null) {
            membre = new Etudiant();
            membre.setNom(membreRequestBody.getNom());
            membre.setPrenom(membreRequestBody.getPrenom());
            ((Etudiant) membre).setNiveau(membreRequestBody.getNiveau());
        } else {
            membre = new Professeur();
            membre.setNom(membreRequestBody.getNom());
            membre.setPrenom(membreRequestBody.getPrenom());
            ((Professeur) membre).setDepartement(membreRequestBody.getDepartement());
        }
        membreRepository.save(membre);
    }

    // Mémorial de ma santé mentale. Ici, le dimanche 23 juin 2024, une barrière morale à failli tomber.
    @Override
    public void updateMembre(Long id, MembreRequestBody membreRequestBody) {
        Optional<Membre> membreInDB = membreRepository.findById(id);
        if (membreInDB.isPresent()) {
            Membre membre = membreInDB.get();
            if (membreRequestBody.getNom() != null) {
                membre.setNom(membreRequestBody.getNom());
            }
            if (membreRequestBody.getPrenom() != null) {
                membre.setPrenom(membreRequestBody.getPrenom());
            }
            if (membre instanceof Etudiant) {
                Etudiant etudiant = (Etudiant) membre;
                if (membreRequestBody.getNiveau() != null) {
                    etudiant.setNiveau(membreRequestBody.getNiveau());
                }
            } else if (membre instanceof Professeur) {
                Professeur professeur = (Professeur) membre;
                if (membreRequestBody.getDepartement() != null) {
                    professeur.setDepartement(membreRequestBody.getDepartement());
                }
            }
            membreRepository.save(membre);
        }
    }

    @Override
    public void deleteMembre(Long id) {
        membreRepository.deleteById( id);
    }
}