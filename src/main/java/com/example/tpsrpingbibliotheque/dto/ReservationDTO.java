package com.example.tpsrpingbibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private int id;
    private String livreId;
    private String membreId;
    private LocalDate dateReservation;
    private LocalDate dateExpiration;
}
