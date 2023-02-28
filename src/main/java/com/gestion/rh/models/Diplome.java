package com.gestion.rh.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Diplome {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String niveau;
    private String ecole;
    private String type_ecole;
    private String type_diplome;
    private String promotion;

    @OneToMany(mappedBy = "diplome")
    private List<Collaborateur> collaborateurList;


}
