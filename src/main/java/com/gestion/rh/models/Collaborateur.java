package com.gestion.rh.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


import java.time.Instant;
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
    private String prenom;
    private String nom;
    private String email;
    private int matricule;
    private String abreviation ;
    private String manager_rh;
    @Pattern(regexp = "^[FM]$")
    private String sexe;
    private String site;
    @Pattern(regexp = "^[A-Z]{3}[0-9]{1}$")
    private String BU ;
    private String mois_bap ;

    private Instant Date_Dpart;

    private boolean ancien_Collaborateur;
    private boolean integration_semaine;

    private Instant Date_Participation;
    @Pattern(regexp = "^[A-Z]{2}[0-9]{1}$")
    private String Poste_App;
    @Pattern(regexp = "^[A-Z]{3}[0-9]{1}$")
    private String Poste_Actuel ;
    private String salaire ;
    @ManyToOne
    @JoinColumn(name = "diplome_id")
    private Diplome diplome ;

    private List<Competence> competences;

}
