package com.example.tpsrpingbibliotheque.repositories;

import com.example.tpsrpingbibliotheque.entities.Emprunt;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
    Emprunt findById(long id);
    Emprunt findByMembre(Membre membre);
    Emprunt findByLivre(Livre livre);
    @Query(value= "DELETE FROM bibliotheque.emprunt WHERE id = ?1", nativeQuery = true)
    @Modifying
    @Transactional
    void deleteById(Long id);
}