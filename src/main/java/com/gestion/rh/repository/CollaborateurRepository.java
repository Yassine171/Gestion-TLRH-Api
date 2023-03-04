package com.gestion.rh.repository;

import com.gestion.rh.models.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {

    public List<Collaborateur> findByManagerRhIsNull();

}
