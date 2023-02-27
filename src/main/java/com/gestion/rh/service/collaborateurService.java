package com.gestion.rh.service;


import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.models.NotificationEmail;
import com.gestion.rh.repository.CollaborateurRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
