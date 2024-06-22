package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.LivreDTO;

import java.util.stream.Stream;

public interface LivreInterface {
    Stream<LivreDTO> getAllLivres();
    LivreDTO getLivre(Long id);
    void createLivre(LivreDTO livreDTO);
    void updateLivre(Long id, LivreDTO livreDTO);
    void deleteLivre(Long id);
}

