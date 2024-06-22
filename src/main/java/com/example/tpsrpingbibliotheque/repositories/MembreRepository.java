package com.example.tpsrpingbibliotheque.repositories;

import com.example.tpsrpingbibliotheque.entities.Membre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembreRepository extends JpaRepository<Membre, Long> {
    Membre findById(long id);
}

