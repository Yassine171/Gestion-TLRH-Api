package com.gestion.rh.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Library;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Competence {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String competence;
    private String niveau;

    @ManyToOne
    @JoinColumn(name="colloborateur_id")
    private Collaborateur colloborateur;
}
