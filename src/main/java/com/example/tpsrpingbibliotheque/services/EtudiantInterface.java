package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EtudiantRequestBody;
import com.example.tpsrpingbibliotheque.entities.Etudiant;

import java.util.List;

public interface EtudiantInterface {
    Etudiant createEtudiant(EtudiantRequestBody etudiantRequestBody);
    List<Etudiant> getAllEtudiants();
    Etudiant getEtudiant(int id);
    void deleteEtudiant(int id);
}

