package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;

import java.util.stream.Stream;

public interface ReservationInterface {
Stream<ReservationDTO> getReservations();
ReservationDTO getReservation(Long id);
void createReservation(Membre membre, Livre livre);
void deleteReservation(Long id);
}

