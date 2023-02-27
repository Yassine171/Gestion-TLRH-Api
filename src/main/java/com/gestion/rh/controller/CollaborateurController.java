package com.gestion.rh.controller;

import com.gestion.rh.models.Collaborateur;
import com.gestion.rh.service.collaborateurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collaborateur")
@RequiredArgsConstructor
public class CollaborateurController {

    @Autowired
    private collaborateurService collaborateurService;
    @PostMapping()
    public Collaborateur saveUser(@RequestBody Collaborateur collaborateur) {
        return collaborateurService.save(collaborateur);
    }


    @GetMapping
    public ResponseEntity<List<Collaborateur>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collaborateurService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborateur> getSubreddit(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(collaborateurService.getCollaborateur(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Collaborateur> updateUser(@PathVariable("id") Long id,
                                           @RequestBody Collaborateur collaborateur) {
        collaborateur = collaborateurService.updateCollaborateur(id,collaborateur);
        return ResponseEntity.ok(collaborateur);
    }

}
