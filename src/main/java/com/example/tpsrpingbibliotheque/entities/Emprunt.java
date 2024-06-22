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
@Table(name="Emprunt")
public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livre", nullable = true)
    private Livre livre;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "membre", nullable = true)
    private Membre membre;

    @Column(name="since")
    private LocalDate dateEmprunt;
}
