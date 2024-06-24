package com.example.tpsrpingbibliotheque.dto;

import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpruntDTO {
    private Long id;
    private Long livre;
    private Long membre;
    private LocalDate dateEmprunt;
}
