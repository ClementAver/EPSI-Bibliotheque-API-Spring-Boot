package com.example.tpsrpingbibliotheque.mappers;

import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import com.example.tpsrpingbibliotheque.entities.Reservation;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ReservationDTOMapper implements Function<Reservation, ReservationDTO> {
    @Override
    public ReservationDTO apply(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getLivre().getId(), reservation.getMembre().getId(), reservation.getDateReservation(), reservation.getDateExpiration());
    }
}


