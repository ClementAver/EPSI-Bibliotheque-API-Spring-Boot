package com.example.tpsrpingbibliotheque.entities;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.enums.Categorie;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
import com.example.tpsrpingbibliotheque.services.Empruntable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="livre")
public class Livre implements Empruntable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "auteur", nullable = false)
    private String auteur;

    @Column(name = "categorie", nullable = false)
    private Categorie categorie;

    @Column(name = "disponible")
    private boolean disponible = true;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "emprunt", nullable = true)
    private Emprunt emprunt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation", nullable = true)
    private Reservation reservation;

    @Override
    public void emprunter() throws LivreNonDisponibleExeption {
        if (this.isDisponible()) {
            this.setDisponible(false);
        } else {
            throw new LivreNonDisponibleExeption("Le livre est déjà emprunté.");
        }
    }

    @Override
    public String rendre() throws LivreNonDisponibleExeption {
        if (!this.isDisponible()) {
            this.setDisponible(true);
        } else {
            throw new LivreNonDisponibleExeption("Le livre est déjà en rayon.");
        }
        return null;
    }
}
