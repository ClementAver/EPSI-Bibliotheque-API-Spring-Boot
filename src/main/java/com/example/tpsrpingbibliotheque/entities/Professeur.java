package com.example.tpsrpingbibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="professeur")
public class Professeur extends Membre {
    @Column(name = "departement", nullable = false)
    private String departement;
}
