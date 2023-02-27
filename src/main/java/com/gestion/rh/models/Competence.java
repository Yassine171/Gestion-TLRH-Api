package com.gestion.rh.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
