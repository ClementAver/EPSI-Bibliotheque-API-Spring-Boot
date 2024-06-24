package com.example.tpsrpingbibliotheque.services;

import com.example.tpsrpingbibliotheque.dto.EmpruntDTO;
import com.example.tpsrpingbibliotheque.dto.LivreDTO;
import com.example.tpsrpingbibliotheque.dto.ReservationDTO;
import com.example.tpsrpingbibliotheque.entities.Emprunt;
import com.example.tpsrpingbibliotheque.entities.Livre;
import com.example.tpsrpingbibliotheque.entities.Membre;
import com.example.tpsrpingbibliotheque.entities.Reservation;
import com.example.tpsrpingbibliotheque.enums.Categorie;
import com.example.tpsrpingbibliotheque.exeptions.LivreNonDisponibleExeption;
import com.example.tpsrpingbibliotheque.mappers.LivreDTOMapper;
import com.example.tpsrpingbibliotheque.repositories.EmpruntRepository;
import com.example.tpsrpingbibliotheque.repositories.LivreRepository;
import com.example.tpsrpingbibliotheque.repositories.MembreRepository;
import com.example.tpsrpingbibliotheque.repositories.ReservationRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class LivreService implements LivreInterface {
    private final MembreRepository membreRepository;
    private LivreRepository livreRepository;
    private final LivreDTOMapper livreDTOMapper;
    private final EmpruntRepository empruntRepository;
    private final EmpruntService empruntService;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    @PersistenceContext
    private EntityManager entityManager;

    public LivreService(LivreRepository livreRepository, EmpruntRepository empruntRepository, ReservationRepository reservationRepository, MembreRepository membreRepository) {
        this.livreRepository = livreRepository;
        this.livreDTOMapper = new LivreDTOMapper();
        this.empruntRepository = empruntRepository;
        this.empruntService = new EmpruntService(empruntRepository, livreRepository, membreRepository);
        this.reservationRepository = reservationRepository;
        this.reservationService = new ReservationService(reservationRepository);
        this.membreRepository = membreRepository;
    }

    @Override
    public Stream<LivreDTO> getAllLivres() {
        return livreRepository.findAll()
                .stream().map(livreDTOMapper);
    }

    @Override
    public LivreDTO getLivre(Long id) {
        Optional<Livre> livreInDB = livreRepository.findById(id);
        if (livreInDB.isPresent()) {
            Livre livre = livreInDB.get();
            return new LivreDTO(livre.getId(), livre.getTitre(), livre.getAuteur(), livre.isDisponible(), livre.getCategorie().toString());
        }
        return null;
    }

    @Override
    public void createLivre(LivreDTO livreDTO) {
        Livre livre = new Livre();
        livre.setTitre(livreDTO.getTitre());
        livre.setAuteur(livreDTO.getAuteur());
        livre.setCategorie(Categorie.valueOf(livreDTO.getCategorie()));
        livre.setDisponible(true);
        livreRepository.save(livre);
    }

    @Override
    public void updateLivre(Long id, LivreDTO livreDTO) {
        Optional<Livre> livreInDB = livreRepository.findById(id);
        if (livreInDB.isPresent()) {
            Livre livre = livreInDB.get();
            if (livre.getTitre().equals(livreDTO.getTitre())) {
                livre.setTitre(livreDTO.getTitre());
            }
            if (livre.getAuteur().equals(livreDTO.getAuteur())) {
                livre.setAuteur(livreDTO.getAuteur());
            }
            if (livre.isDisponible() == livreDTO.isDisponible()) {
                livre.setDisponible(livreDTO.isDisponible());
            }
            if (livre.getCategorie().toString().equals(livreDTO.getCategorie())) {
                livre.setCategorie(Categorie.valueOf(livreDTO.getCategorie()));
            }
            livreRepository.save(livre);
        }
    }

    @Override
    public void deleteLivre(Long id) {
        livreRepository.deleteById(id);
    }

    public void reserver(ReservationDTO reservationDTO) throws LivreNonDisponibleExeption {
        Membre membre;
        Livre livre;

        Optional<Livre> livreInDB = livreRepository.findById(reservationDTO.getLivre());
        // Le livre est-il en BDD ?
        if (livreInDB.isPresent()) {
            livre = livreInDB.get();
        } else {
            throw new LivreNonDisponibleExeption("Livre inconnu.");
        }

        Optional<Membre> membreInDB = membreRepository.findById(reservationDTO.getMembre());
        // Le membre est-il en BDD.
        if (membreInDB.isPresent()) {
            membre = membreInDB.get();
            // Le membre peut-il réserver un livre ?
            if (membre.getReservations().size() > 1) {
                throw new LivreNonDisponibleExeption("Nombre de réservations maximum atteintes.");
            }
        }
        else {
            throw new LivreNonDisponibleExeption("Membre inconnu.");
        }

        Reservation reservationInDB = reservationRepository.findByLivre(livre);
        // Le livre est-il déjà réservé ?
        if (reservationInDB != null) {
            // La réservation est-elle toujours valide (date d'expiration postérieure à la date actuelle) ?
            if (reservationInDB.getDateExpiration().isAfter(LocalDate.now())) {
                if (reservationInDB.getMembre().equals(membre)) {
                    throw new LivreNonDisponibleExeption("Vous avez déjà réservé ce livre.");
                }
                throw new LivreNonDisponibleExeption("Ce livre est déjà réservé.");
            } else {
                reservationRepository.delete(reservationInDB);
            }
        }

        reservationService.createReservation(membre, livre);
    }

    public void emprunter(EmpruntDTO empruntDTO) throws LivreNonDisponibleExeption {
        Livre livre;
        Membre membre;

        Optional<Livre> livreInDB = livreRepository.findById(empruntDTO.getLivre());
        // Le livre est-il en BDD ?
        if (livreInDB.isPresent()) {
            livre = livreInDB.get();
        } else {
            throw new LivreNonDisponibleExeption("Livre inconnu.");
        }

        Optional<Membre> membreInDB = membreRepository.findById(empruntDTO.getMembre());
        // Le membre est-il en BDD.
        if (membreInDB.isPresent()) {
            membre = membreInDB.get();
            // Le membre peut-il emprunter un livre ?
            if (membre.getEmprunts().size() > 2) {
                throw new LivreNonDisponibleExeption("Nombre d'emprunts maximum atteints.");
            }
        }
        else {
            throw new LivreNonDisponibleExeption("Membre inconnu.");
        }

        // Le livre est-il réservé ?
        if (livre.getReservation() != null) {
            // La réservation est-elle encore valable (date d'expiration postérieure à la date actuelle)
            // OU la réservation est-elle au nom du membre souhaitant emprunter ce livre ?
            if (livre.getReservation().getDateExpiration().isBefore(LocalDate.now()) || Objects.equals(livre.getReservation().getMembre().getId(), empruntDTO.getMembre())) {
                // Si le livre est disponible, il est emprunté.
                try {
                    livre.emprunter();
                    empruntService.createEmprunt(membre, livre);
                } catch (LivreNonDisponibleExeption e) {
                    throw new LivreNonDisponibleExeption(e.getMessage());
                }
                // La réservation est donc supprimée.
                reservationRepository.delete(livre.getReservation());
            }
            else  {
                throw new LivreNonDisponibleExeption("Ce livre est déjà réservé.");
            }
        } else {
            try {
                livre.emprunter();
                empruntService.createEmprunt(membre, livre);

            } catch (LivreNonDisponibleExeption e) {
                throw new LivreNonDisponibleExeption(e.getMessage());
            }
            livreRepository.save(livre);
        }
        livreRepository.save(livre);
    }

    @Transactional
    public String rendre(EmpruntDTO empruntDTO) throws LivreNonDisponibleExeption {
        String statement = null;
        Livre livre;

        Optional<Livre> livreInDB = livreRepository.findById(empruntDTO.getLivre());
        // Le livre est-il en BDD ?
        if (livreInDB.isPresent()) {
            livre = livreInDB.get();
        } else {
            throw new LivreNonDisponibleExeption("Livre inconnu.");
        }

        Emprunt emprunt = empruntRepository.findByLivre(livre);
        try {
            livre.rendre();
            empruntRepository.deleteById(emprunt.getId());
            entityManager.flush();
            entityManager.clear();

            statement = "Le livre à bien été rendu.";
            Period period = Period.between(empruntDTO.getDateEmprunt(), LocalDate.now());
            int daysBetween = period.getDays();
            if (daysBetween > 14) {
                statement = "Vous avez reçu une pénalité de " + (daysBetween-14) + "€.";
            }
        } catch (LivreNonDisponibleExeption e) {
            throw new LivreNonDisponibleExeption(e.getMessage());
        }

        return statement;
    }
}