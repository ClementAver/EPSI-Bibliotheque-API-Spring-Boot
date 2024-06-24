package com.example.tpsrpingbibliotheque.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Membre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @Column(name = "prenom", nullable = false)
    private String prenom;

    @Column
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Emprunt> emprunts = new LinkedHashSet<>();

    @Column
    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private Set<Reservation> reservations = new LinkedHashSet<>();
}