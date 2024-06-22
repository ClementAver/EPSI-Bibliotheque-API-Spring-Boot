package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.MembreDTO;
import com.example.tpsrpingbibliotheque.dto.MembreRequestBody;

import java.util.stream.Stream;

public interface MembreInterface {
    Stream<MembreDTO> getAllMembres();
    MembreDTO getMembre(Long id);
    void createMembre(MembreRequestBody etudiantRequestBody);
    void updateMembre(Long id, MembreRequestBody membreRequestBody);
    void deleteMembre(Long id);
}

