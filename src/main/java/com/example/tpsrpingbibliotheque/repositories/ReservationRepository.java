package com.example.tpsrpingbibliotheque.repositories;

import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Reservation findById(long id);
    Reservation findByMembre(Membre membre);
    Reservation findByLivre(Livre livre);
}
