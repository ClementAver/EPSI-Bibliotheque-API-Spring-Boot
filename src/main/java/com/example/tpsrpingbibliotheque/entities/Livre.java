package com.example.tpsrpingbibliotheque.entities;

import com.example.tpsrpingbibliotheque.enums.Categorie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="\"livre\"")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titre", nullable = false)
    private String titre;

    @Column(name = "auteur", nullable = false)
    private String auteur;

    @Column(name = "categorie", nullable = false)
    Categorie categorie;

    @Column(name = "disponible")
    boolean disponible =true;
}
