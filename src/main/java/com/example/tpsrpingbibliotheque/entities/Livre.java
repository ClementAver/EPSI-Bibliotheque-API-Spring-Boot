package com.example.tpsrpingbibliotheque.entities;

import com.example.tpsrpingbibliotheque.enums.Categorie;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
import com.example.tpsrpingbibliotheque.services.Empruntable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="livre")
public class Livre implements Empruntable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

//    @JoinColumn(name = "emprunt", nullable = true, referencedColumnName = "id")
    @OneToOne(mappedBy = "livre", cascade = CascadeType.ALL)
    private Emprunt emprunt;

//    @JoinColumn(name = "reservation", nullable = true, referencedColumnName = "id")
    @OneToOne(mappedBy = "livre", cascade = {CascadeType.ALL})
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
