package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
import com.example.tpsrpingbibliotheque.repositories.LivreRepository;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreService implements LivreInterface {

    private final DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration;
    private LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository, DataSourceTransactionManagerAutoConfiguration dataSourceTransactionManagerAutoConfiguration) {
        this.livreRepository = livreRepository;
        this.dataSourceTransactionManagerAutoConfiguration = dataSourceTransactionManagerAutoConfiguration;
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

    @Override
    public void empruntLivre(int id, boolean emprunt, LivreRequestBody livreRequestBody) throws LivreNonDisponibleExeption {
        try {
            Livre livre = livreRepository.findById(id);
            if (livre == null) throw new LivreNonDisponibleExeption("Livre inconnu.");

            if (emprunt) {
                if (livre.isDisponible()) {
                    livre.setDisponible(false);
                    livreRepository.save(livre);
                } else {
                    throw new LivreNonDisponibleExeption("Le livre est déjà emprunté.");
                }
            } else {
                if (!livre.isDisponible()) {
                    livre.setDisponible(true);
                    livreRepository.save(livre);
                } else {
                    throw new LivreNonDisponibleExeption("Le livre est déjà en rayon.");
                }
            }
        } catch (LivreNonDisponibleExeption e) {
            System.out.println(e.getMessage());
        }

    }

}