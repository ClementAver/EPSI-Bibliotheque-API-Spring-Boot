package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;

public interface Empruntable {
    void emprunter() throws LivreNonDisponibleExeption;
    String rendre() throws LivreNonDisponibleExeption;
}
