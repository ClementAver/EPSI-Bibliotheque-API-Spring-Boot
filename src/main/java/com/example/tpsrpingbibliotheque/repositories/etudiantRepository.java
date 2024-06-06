package com.example.tpsrpingbibliotheque.repositories;

import com.example.tpsrpingbibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface etudiantRepository extends JpaRepository<Livre, Long> {

}

