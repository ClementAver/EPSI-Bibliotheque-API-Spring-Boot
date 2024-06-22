package com.example.tpsrpingbibliotheque.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

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
    @OneToMany(mappedBy = "id", cascade = {PERSIST, MERGE})
    private Set<Livre> emprunts = new LinkedHashSet<>();

    @Column
    @OneToMany(mappedBy = "id", cascade = {PERSIST, MERGE})
    private Set<Reservation> reservations = new LinkedHashSet<>();
}