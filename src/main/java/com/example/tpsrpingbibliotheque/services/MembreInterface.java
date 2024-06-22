package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.MembreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.repositories.MembreRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface MembreInterface {
    Membre createMembre(MembreRequestBody etudiantRequestBody);
    Set<Membre> getAllMembres();
    Membre getMembre(int id);
    void deleteMembre(int id);
}

