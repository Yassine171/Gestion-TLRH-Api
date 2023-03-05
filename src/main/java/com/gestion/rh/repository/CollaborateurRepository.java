package com.gestion.rh.repository;

import com.gestion.rh.models.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {

    public List<Collaborateur> findByManagerRhIsNull();

    @Query(value = "SELECT YEAR(c.Date_Participation) as year, AVG(CAST(salaire AS double)) as averageSalaire FROM Collaborateur c GROUP BY YEAR(c.Date_Participation)")
    List<Object[]> findAverageSalaireByYear();


}
