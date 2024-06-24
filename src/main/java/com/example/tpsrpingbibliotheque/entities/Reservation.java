package com.example.tpsrpingbibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
    private Long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "livre", nullable = false, unique = true)
    @EqualsAndHashCode.Exclude private Livre livre;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "membre", nullable = false)
    private Membre membre;

    @Column(name = "date-reservation", nullable = false)
    private LocalDate dateReservation;

    @Column(name = "date-expiration", nullable = false)
    private LocalDate dateExpiration;
}

