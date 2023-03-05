package com.gestion.rh.controller;

import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.models.Competence;
import com.gestion.rh.models.Diplome;
import com.gestion.rh.models.ManagerRh;
import com.gestion.rh.repository.CompetenceRepository;
import com.gestion.rh.repository.ManagerRhRepository;
import com.gestion.rh.repository.CollaborateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managerRh")
@RequiredArgsConstructor
public class ManagerRhController {
    private final CollaborateurRepository collaborateurRepository;
    private final ManagerRhRepository managerRhRepository;
    private final CompetenceRepository competenceRepository;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('Ambassadeur Rh')")
    public ResponseEntity<?> addManerRh(@PathVariable Long id) {
        Optional<Collaborateur> collaborateurOpt = collaborateurRepository.findById(id);
        if (collaborateurOpt.isPresent()) {
            Collaborateur collaborateur = collaborateurOpt.get();
            List<Competence> competences = collaborateur.getCompetences();
            Diplome diplome = collaborateur.getDiplome();
            for (Competence competence : competences) {
                competence.setColloborateur(null);
            }
            diplome.setCollaborateurList(null);
            collaborateur.setDiplome(null);
            collaborateur.setCompetences(null);
            collaborateurRepository.delete(collaborateur);

            ManagerRh managerRh = ManagerRh.builder()
                    .abreviation(collaborateur.getAbreviation())
                    .BU(collaborateur.getBU())
                    .email(collaborateur.getEmail())
                    .nom(collaborateur.getNom())
                    .Date_Participation(collaborateur.getDate_Participation())
                    .matricule(collaborateur.getMatricule())
                    .mois_bap(collaborateur.getMois_bap())
                    .sexe(collaborateur.getSexe())
                    .site(collaborateur.getSite())
                    .Date_Dpart(collaborateur.getDate_Dpart())
                    .Poste_App(collaborateur.getPoste_App())
                    .salaire(collaborateur.getSalaire())
                    .Poste_Actuel(collaborateur.getPoste_Actuel())
                    .prenom(collaborateur.getPrenom())
                    .competences(competences)
                    .diplome(diplome)
                    .build();

            managerRhRepository.save(managerRh);

            for (Competence competence : competences) {
                competence.setManagerRh(managerRh);
                competenceRepository.save(competence);
            }

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(managerRh);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Collaborateur not found");
        }
    }

    @GetMapping("/status/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('Ambassadeur Rh')")
    public ResponseEntity<?> toogleStatus(@PathVariable Long id) {
        Optional<ManagerRh> managerRhOpt = managerRhRepository.findById(id);
        if (managerRhOpt.isPresent()) {
            ManagerRh managerRh = managerRhOpt.get();
            managerRh.setStatus(!managerRh.isStatus());
            managerRhRepository.save(managerRh);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(managerRh);
        }
        else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Collaborateur not found");
        }
    }

    @GetMapping("/{idManagerRh}/collaborateurs")
    @PreAuthorize("hasRole('USER') or hasRole('Ambassadeur Rh')")
    public ResponseEntity<?> getCollaborateursSansManagerRh(@PathVariable Long idManagerRh) {
        Optional<ManagerRh> managerRhOpt = managerRhRepository.findById(idManagerRh);
        if (managerRhOpt.isPresent()) {
            List<Collaborateur> collaborateurs = collaborateurRepository.findByManagerRhIsNull();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(collaborateurs);
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Manager RH not found");
        }
    }


    @PostMapping("/manager/{id}/collaborateurs")
    public ResponseEntity<?> assignCollaborateurToManager(@PathVariable Long id, @RequestBody Long collaborateurId) {
        Optional<ManagerRh> managerRhOpt = managerRhRepository.findById(id);
        if (!managerRhOpt.isPresent()) {
            // handle managerRh not found error
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Manager RH not found");
        }

        Optional<Collaborateur> collaborateurOpt = collaborateurRepository.findById(collaborateurId);
        if (!collaborateurOpt.isPresent()) {
            // handle collaborateur not found error
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Collaborateur not found");
        }

        ManagerRh managerRh = managerRhOpt.get();
        Collaborateur collaborateur = collaborateurOpt.get();

        if (managerRh.isStatus() == false) {
            // handle managerRh not activated error
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Manager RH is not activated");
        }

        collaborateur.setManagerRh(managerRh);
        collaborateurRepository.save(collaborateur);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Collaborateur successfully assigned to Manager RH");
    }
}
