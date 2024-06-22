package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import com.example.tpsrpingbibliotheque.entities.Reservation;

import java.util.stream.Stream;

public interface ReservationInterface {
Stream<ReservationDTO> getReservations();
ReservationDTO getReservation(int id);
void createReservation(ReservationDTO reservationDTO);
}

