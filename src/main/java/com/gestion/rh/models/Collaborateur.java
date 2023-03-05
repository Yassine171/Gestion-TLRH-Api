package com.gestion.rh.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

import java.util.List;


import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Collaborateur {


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
    private String ancien_manager_rh;
    private String manager_rh;
    @Pattern(regexp = "^[FM]$", message = "Invalid sexe selemnet F et M")
    private String sexe;
    private String site;
    @Pattern(regexp = "^[A-Z]{3}[0-9]{1}$", message = "Invalid BU trois charactere et un  numero")
    private String BU ;
    private String mois_bap ;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_Dpart;

    private boolean ancien_Collaborateur;
    private boolean integration_semaine;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate Date_Participation;
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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "manager_rh_id")
    private ManagerRh managerRh;

}
