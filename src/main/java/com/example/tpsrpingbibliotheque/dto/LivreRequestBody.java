package com.example.tpsrpingbibliotheque.dto;

import com.example.tpsrpingbibliotheque.enums.Categorie;
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
public class LivreRequestBody {
    String titre;
    String auteur;
    Categorie categorie;
    boolean disponible =true;

    public String toString() {
        log.info("mon objet");
        return super.toString();
    }
}