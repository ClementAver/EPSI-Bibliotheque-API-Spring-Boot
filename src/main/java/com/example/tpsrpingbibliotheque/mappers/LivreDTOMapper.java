package com.example.tpsrpingbibliotheque.mappers;

import com.example.tpsrpingbibliotheque.dto.LivreDTO;
import com.example.tpsrpingbibliotheque.entities.Livre;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class LivreDTOMapper implements Function<Livre, LivreDTO> {
    @Override
    public LivreDTO apply(Livre livre) {
        return new LivreDTO(livre.getId(), livre.getTitre(), livre.getAuteur(), livre.isDisponible(), livre.getCategorie().toString());
    }
}


