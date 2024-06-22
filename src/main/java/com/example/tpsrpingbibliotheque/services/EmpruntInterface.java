package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;

import java.util.stream.Stream;

public interface EmpruntInterface {
    Stream<EmpruntDTO> getEmprunts();
    EmpruntDTO getEmprunt(Long id);
    void createEmprunt(EmpruntDTO empruntDTO);
    void deleteEmprunt(Long id);
}
