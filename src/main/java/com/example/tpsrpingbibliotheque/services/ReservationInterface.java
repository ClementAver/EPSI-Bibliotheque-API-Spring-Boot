package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import java.util.stream.Stream;

public interface ReservationInterface {
Stream<ReservationDTO> getReservations();
ReservationDTO getReservation(Long id);
void createReservation(ReservationDTO reservationDTO);
void deleteReservation(Long id);
}

