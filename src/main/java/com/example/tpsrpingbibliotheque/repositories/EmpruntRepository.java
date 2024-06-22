package com.example.tpsrpingbibliotheque.repositories;

import com.example.tpsrpingbibliotheque.entities.Emprunt;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    Emprunt findById(long id);
    Emprunt findByMembre(Membre membre);
    Emprunt findByLivre(Livre livre);
}
