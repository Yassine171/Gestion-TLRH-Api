package com.gestion.rh.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRh {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotBlank(message = "Prenom is required")
    private String prenom;
    @NotBlank(message = "Nom is required")
    private String nom;

    @Email
    @NotBlank(message = "Email is required")
    private String email;
    @Pattern(regexp = "^[0-9]{5}$", message = "Invalid matricule cinq numero seulement")
    private String matricule;
    private String abreviation ;

    @Pattern(regexp = "^[FM]$", message = "Invalid sexe selemnet F et M")
    private String sexe;
    private String site;
    @Pattern(regexp = "^[A-Z]{3}[0-9]{1}$", message = "Invalid BU trois charactere et un  numero")
    private String BU ;
    private String mois_bap ;

    private Instant Date_Dpart;

    private Instant Date_Participation;
    @Pattern(regexp = "^[A-Z]{2}[0-9]{1}$", message = "Invalid Poste")
    private String Poste_App;
    @Pattern(regexp = "^[A-Z]{3}[0-9]{1}$", message = "Invalid Poste")
    private String Poste_Actuel ;
    private String salaire ;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diplome_id")
    private Diplome diplome ;

    @OneToMany(mappedBy = "colloborateur",cascade = CascadeType.ALL)
    private List<Competence> competences;
}
