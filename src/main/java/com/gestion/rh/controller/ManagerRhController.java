package com.gestion.rh.controller;

import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.models.ManagerRh;
import com.gestion.rh.repository.CollaborateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/managerRh")
@RequiredArgsConstructor
public class ManagerRhController {
    private final CollaborateurRepository collaborateurRepository;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('Ambassadeur Rh')")
    public ResponseEntity<?> addManerRh(@PathVariable Long id) {
        Optional<Collaborateur> collaborateur=collaborateurRepository.findById(id);
        ManagerRh managerRh = ManagerRh.builder()
                .abreviation(collaborateur.get().getAbreviation())
                .BU(collaborateur.get().getBU())
                .email(collaborateur.get().getEmail())
                .nom(collaborateur.get().getNom())
                .Date_Participation(collaborateur.get().getDate_Participation())
                .matricule(collaborateur.get().getMatricule())
                .mois_bap(collaborateur.get().getMois_bap())
                .sexe(collaborateur.get().getSexe())
                .site(collaborateur.get().getSite())
                .Date_Dpart(collaborateur.get().getDate_Dpart())
                .Poste_App(collaborateur.get().getPoste_App())
                .salaire(collaborateur.get().getSalaire())
                .Poste_Actuel(collaborateur.get().getPoste_Actuel())
                .prenom(collaborateur.get().getPrenom())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(managerRh);
    }
}
