package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;

import java.util.stream.Stream;

public interface EmpruntInterface {
    Stream<EmpruntDTO> getEmprunts();
    EmpruntDTO getEmprunt(Long id);
    void createEmprunt(Membre membre, Livre livre);
    void deleteEmprunt(Long id);
}
