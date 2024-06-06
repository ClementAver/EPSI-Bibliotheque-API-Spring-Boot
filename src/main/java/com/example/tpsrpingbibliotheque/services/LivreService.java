package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.repositories.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService implements LivreInterface {

    private LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    @Override
    public Livre createLivre(LivreRequestBody livreRequestBody) {
        Livre livre = new Livre();
        livre.setTitre(livreRequestBody.getTitre());
        livre.setAuteur(livreRequestBody.getAuteur());
        livre.setCategorie(livreRequestBody.getCategorie());
        livre.setDisponible(livreRequestBody.isDisponible());

        return livreRepository.save(livre);
    }

    @Override
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @Override
    public Livre getLivre(int id) {
        return livreRepository.findById(id);
    }

    @Override
    public void deleteLivre(int id) {
        livreRepository.deleteById((long) id);
    }
}