package com.gestion.rh.service;


import com.gestion.rh.exceptions.RhException;
import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.models.Competence;
import com.gestion.rh.models.NotificationEmail;
import com.gestion.rh.repository.CollaborateurRepository;
import com.gestion.rh.repository.CompetenceRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class collaborateurService {
  private final CollaborateurRepository collaborateurRepository;
  private final CompetenceRepository competenceRepository;
  private final MailService mailService;
    @Transactional
    public Collaborateur save(Collaborateur collaborateur) {
        Collaborateur savedCollaborateur = collaborateurRepository.save(collaborateur);
        List<Competence> competences = collaborateur.getCompetences();
        if (competences != null && !competences.isEmpty()) {
            for (Competence competence : competences) {
                competence.setColloborateur(savedCollaborateur);
                competenceRepository.save(competence);
            }
        }
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                collaborateur.getEmail(), "Bienvenu\n\n"+"le groupe SQLI occupe une position centrale dans le marché des NTIC. Cette large base en termes de ressources humaines nécessite une informatisation de l'ensemble des pratiques mises en œuvre pour administrer, gérer et structurer ces ressources impliquées dans l'activité du groupe." ));
        return savedCollaborateur;
    }
    @Transactional(readOnly = true)
    public List<Collaborateur> getAll() {
        return collaborateurRepository.findAll()
                .stream()
                .collect(toList());
    }

    public Collaborateur getCollaborateur(Long id) {
        Collaborateur collaborateur = collaborateurRepository.findById(id)
                .orElseThrow(() -> new RhException("No collaborateur found with ID - " + id));
        return collaborateur;
    }

    public Collaborateur updateCollaborateur(Long id, Collaborateur collaborateur) {
        Collaborateur collaborateurAncien =
                collaborateurRepository.findById(id).get();
        collaborateurAncien.setSalaire(collaborateur.getSalaire());
        collaborateurAncien.setPoste_App(collaborateur.getPoste_App());
        collaborateurAncien.setManager_rh(collaborateur.getManager_rh());

        collaborateurRepository.save(collaborateurAncien);
        return collaborateurAncien;
    }

}
