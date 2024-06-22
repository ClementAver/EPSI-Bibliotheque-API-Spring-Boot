package com.example.tpsrpingbibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivreDTO {
    private Long id;
    private String titre;
    private String auteur;
    private boolean disponible;
    private String categorie;
}
