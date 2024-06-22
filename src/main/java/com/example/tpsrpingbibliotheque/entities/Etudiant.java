package com.example.tpsrpingbibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="etudiant")
public class Etudiant extends Membre {
    @Column(name = "niveau", nullable = false)
    private String niveau;
}
