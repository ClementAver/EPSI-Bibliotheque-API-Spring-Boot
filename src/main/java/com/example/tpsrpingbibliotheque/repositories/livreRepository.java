package com.example.tpsrpingbibliotheque.repositories;

import com.example.tpsrpingbibliotheque.entities.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface livreRepository extends JpaRepository<Livre, Long> {
    Livre findById(long id);
}
