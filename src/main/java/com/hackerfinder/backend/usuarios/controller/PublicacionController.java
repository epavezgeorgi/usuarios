package com.hackerfinder.backend.usuarios.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hackerfinder.backend.usuarios.service.PublicacionService;
import com.hackerfinder.backend.usuarios.model.Publicacion;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public ResponseEntity<List<Publicacion>> getAllPublicaciones() {
        List<Publicacion> publicaciones = publicacionService.getAllPublicaciones();

        if (publicaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(publicaciones);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacion> getPublicacionById(@PathVariable Long id) {
        try {
            Publicacion publicacion = publicacionService.findById(id);
            if (publicacion != null) {
                return ResponseEntity.ok(publicacion);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Publicacion>> getPublicacionesByUsuarioId(@PathVariable Long idUsuario) {
        try {
            List<Publicacion> publicaciones = publicacionService.findByIdUsuario(idUsuario);
            if (publicaciones.isEmpty()) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.ok(publicaciones);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Publicacion> createPublicacion(@RequestBody Publicacion publicacion) {
        try {
            Publicacion nuevaPublicacion = publicacionService.savePublicacion(publicacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaPublicacion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}