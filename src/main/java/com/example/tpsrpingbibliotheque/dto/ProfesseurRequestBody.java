package com.example.tpsrpingbibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfesseurRequestBody {
    String nom;
    String prenom;
    String departement;

    public String toString() {
        log.info("mon objet");
        return super.toString();
    }
}