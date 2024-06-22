package com.example.tpsrpingbibliotheque.mappers;

import com.example.tpsrpingbibliotheque.dto.MembreDTO;
import com.example.tpsrpingbibliotheque.entities.Membre;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MembreDTOMapper implements Function<Membre, MembreDTO> {
    @Override
    public MembreDTO apply(Membre membre) {
        return new MembreDTO(membre.getId(), membre.getNom(), membre.getPrenom());
    }
}
