package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EtudiantRequestBody;
import com.example.tpsrpingbibliotheque.entities.Etudiant;
import com.example.tpsrpingbibliotheque.entities.Membre;

import java.util.List;

public interface EtudiantInterface {
    Membre createEtudiant(EtudiantRequestBody etudiantRequestBody);
    List<Membre> getAllEtudiants();
    Membre getEtudiant(int id);
    void deleteEtudiant(int id);
}

