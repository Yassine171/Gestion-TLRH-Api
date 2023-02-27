package com.gestion.rh.controller;

import com.gestion.rh.models.Collaborateur;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collaborateur")
@RequiredArgsConstructor
public class CollaborateurController {

    @PostMapping("/")
    ResponseEntity<String> addUser(@Valid @RequestBody Collaborateur collaborateur) {
        // persisting the user
        return ResponseEntity.ok("User is valid");
    }
}
