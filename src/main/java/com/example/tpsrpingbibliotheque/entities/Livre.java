package com.example.tpsrpingbibliotheque.entities;

import com.example.tpsrpingbibliotheque.dto.LivreRequestBody;
import com.example.tpsrpingbibliotheque.enums.Categorie;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
import com.example.tpsrpingbibliotheque.services.Empruntable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="livre")
public class Livre implements Empruntable {
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
    boolean disponible = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "in-possession-off")
    Membre inPossessionOf;

    @Column(name="since")
    LocalDate since;

    @Override
    public void emprunter(LivreRequestBody livreRequestBody) throws LivreNonDisponibleExeption {
        if (this.isDisponible()) {
            this.setDisponible(false);
            this.setInPossessionOf(null);
            this.setSince(Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        } else {
            throw new LivreNonDisponibleExeption("Le livre est déjà emprunté.");
        }
    }

    @Override
    public String rendre(LivreRequestBody livreRequestBody) throws LivreNonDisponibleExeption {
        if (!this.isDisponible()) {
            this.setDisponible(true);
            LocalDate now = Calendar.getInstance().getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Period period = Period.between(this.since, now);
            int daysBetween = period.getDays();
            if (daysBetween > 14) {
                return "vous avez reçu une pénalité de " + (daysBetween-14);
            }
            this.setInPossessionOf(livreRequestBody.getInPossessionOf());
        } else {
            throw new LivreNonDisponibleExeption("Le livre est déjà en rayon.");
        }
        return null;
    }
}
