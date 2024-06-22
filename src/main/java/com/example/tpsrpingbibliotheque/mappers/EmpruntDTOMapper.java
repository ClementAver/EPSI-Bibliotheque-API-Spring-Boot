package com.example.tpsrpingbibliotheque.mappers;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.entities.Emprunt;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class EmpruntDTOMapper implements Function<Emprunt, EmpruntDTO> {
    @Override
    public EmpruntDTO apply(Emprunt emprunt) {
        return new EmpruntDTO(emprunt.getId(), emprunt.getLivre(), emprunt.getMembre(), emprunt.getDateEmprunt());
    }
}


