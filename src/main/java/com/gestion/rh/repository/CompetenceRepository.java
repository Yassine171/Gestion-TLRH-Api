package com.gestion.rh.repository;

import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.models.Competence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository  extends JpaRepository<Competence, Long> {
}
