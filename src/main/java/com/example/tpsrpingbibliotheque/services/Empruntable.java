package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;

public interface Empruntable {
    void emprunter(LivreRequestBody livreRequestBody) throws LivreNonDisponibleExeption;
    String rendre(LivreRequestBody livreRequestBody) throws LivreNonDisponibleExeption;
}
