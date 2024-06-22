package com.example.tpsrpingbibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    int id;

    @Column(name = "livre", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    Livre livre;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "membre", nullable = false)
    Membre membre;

    @Column(name = "date-reservation", nullable = false)
    LocalDate dateReservation;

    @Column(name = "date-expiration", nullable = false)
    LocalDate dateExpiration;
}
