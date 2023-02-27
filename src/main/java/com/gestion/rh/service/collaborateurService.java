package com.gestion.rh.service;


import com.gestion.rh.exceptions.RhException;
import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.models.NotificationEmail;
import com.gestion.rh.repository.CollaborateurRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class collaborateurService {
  private final CollaborateurRepository collaborateurRepository;
  private final MailService mailService;
    @Transactional
    public Collaborateur save(Collaborateur collaborateur) {
        Collaborateur save = collaborateurRepository.save(collaborateur);
        mailService.sendMail(new NotificationEmail("Please Activate your Account",
                collaborateur.getEmail(), "Thank you for signing up to Spring Reddit, " +
                "please click on the below url to activate your account : " +
                "http://localhost:8081/" ));
        return save;
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
