package com.example.tpsrpingbibliotheque.enums;

import lombok.Getter;

@Getter
public enum Categorie {
    FICTION("fictionnelle"),
    NON_FICTION("non-fictionnelle"),
    SCIENTIFIQUE("scientifique"),
    HISTOIRE("historique"),
    BIOGRAPHIE("biographie");

    private String categorie;

    Categorie(String categorie) {
        this.categorie = categorie;
    }
}

