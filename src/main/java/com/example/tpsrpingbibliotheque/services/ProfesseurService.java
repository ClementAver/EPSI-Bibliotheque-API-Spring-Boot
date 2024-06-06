package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.ProfesseurRequestBody;
import com.example.tpsrpingbibliotheque.entities.Professeur;
import com.example.tpsrpingbibliotheque.repositories.ProfesseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesseurService implements ProfesseurInterface {

    private final ProfesseurRepository professeurRepository;

    public ProfesseurService(ProfesseurRepository professeurRepository) {
        this.professeurRepository = professeurRepository;
    }

    @Override
    public Professeur createProfesseur(ProfesseurRequestBody professeurRequestBody) {
        Professeur professeur = new Professeur();
        professeur.setNom(professeurRequestBody.getNom());
        professeur.setPrenom(professeurRequestBody.getPrenom());
        professeur.setDepartement(professeurRequestBody.getDepartement());

        return professeurRepository.save(professeur);
    }

    @Override
    public List<Professeur> getAllProfesseurs() {
        return professeurRepository.findAll();
    }

    @Override
    public Professeur getProfesseur(int id) {
        return professeurRepository.findById(id);
    }

    @Override
    public void deleteProfesseur(int id) {
        professeurRepository.deleteById((long) id);
    }
}
