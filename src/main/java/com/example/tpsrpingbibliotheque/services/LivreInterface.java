package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;

import java.util.List;

public interface LivreInterface {
    Livre createLivre(LivreRequestBody livreRequestBody);
    List<Livre> getAllLivres();
    Livre getLivre(int id);
    void deleteLivre(int id);
}

