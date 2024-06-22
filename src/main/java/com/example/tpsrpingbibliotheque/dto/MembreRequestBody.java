package com.example.tpsrpingbibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MembreRequestBody {
    String nom;
    String prenom;
    String departement;
    String niveau;
}

