package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.ProfesseurRequestBody;
import com.example.tpsrpingbibliotheque.entities.Professeur;

import java.util.List;

public interface ProfesseurInterface {
    Professeur createProfesseur(ProfesseurRequestBody professeurRequestBody);
    List<Professeur> getAllProfesseurs();
    Professeur getProfesseur(int id);
    void deleteProfesseur(int id);
}

