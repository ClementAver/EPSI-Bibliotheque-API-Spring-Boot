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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "livre")
    private Livre livre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membre", nullable = false)
    private Membre membre;

    @Column(name = "date-reservation", nullable = false)
    private LocalDate dateReservation;

    @Column(name = "date-expiration", nullable = false)
    private LocalDate dateExpiration;
}
