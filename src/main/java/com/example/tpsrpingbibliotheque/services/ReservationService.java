package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.entities.Reservation;
import com.example.tpsrpingbibliotheque.mappers.ReservationDTOMapper;
import com.example.tpsrpingbibliotheque.repositories.ReservationRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

public class ReservationService implements ReservationInterface{

    public final ReservationRepository reservationRepository;
    public final ReservationDTOMapper reservationDTOMapper;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
        this.reservationDTOMapper = new ReservationDTOMapper();
    }

    @Override
    public Stream<ReservationDTO> getReservations() {
        return reservationRepository.findAll()
                .stream().map(reservationDTOMapper);
    }

    @Override
    public ReservationDTO getReservation(Long id) {
        Optional<Reservation> reservationInDB = reservationRepository.findById(id);
        if (reservationInDB.isPresent()) {
            Reservation reservation = reservationInDB.get();
            return new ReservationDTO(reservation.getId(), reservation.getLivre().getId(), reservation.getMembre().getId(), reservation.getDateReservation(), reservation.getDateExpiration());
        }
        return null;
    }

    @Override
    public void createReservation(Membre membre, Livre livre) {
        Reservation reservation = new Reservation();
        reservation.setMembre(membre);
        reservation.setLivre(livre);
        reservation.setDateReservation(LocalDate.now());
        reservation.setDateExpiration(LocalDate.now().plusDays(14));
        reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
