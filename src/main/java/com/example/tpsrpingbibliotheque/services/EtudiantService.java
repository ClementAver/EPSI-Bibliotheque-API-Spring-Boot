package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EtudiantRequestBody;
import com.example.tpsrpingbibliotheque.entities.Etudiant;
import com.example.tpsrpingbibliotheque.repositories.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtudiantService implements EtudiantInterface {

    private final EtudiantRepository etudiantRepository;

    public EtudiantService(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @Override
    public Etudiant createEtudiant(EtudiantRequestBody etudiantRequestBody) {
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(etudiantRequestBody.getNom());
        etudiant.setPrenom(etudiantRequestBody.getPrenom());
        etudiant.setNiveau(etudiantRequestBody.getNiveau());

        return etudiantRepository.save(etudiant);
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    @Override
    public Etudiant getEtudiant(int id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public void deleteEtudiant(int id) {
        etudiantRepository.deleteById((long) id);
    }
}