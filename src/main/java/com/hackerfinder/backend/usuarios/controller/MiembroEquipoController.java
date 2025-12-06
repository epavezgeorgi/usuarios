package com.hackerfinder.backend.usuarios.controller;

import com.hackerfinder.backend.usuarios.model.MiembroEquipo;
import com.hackerfinder.backend.usuarios.service.MiembroEquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/miembros")
public class MiembroEquipoController {

    @Autowired
    private MiembroEquipoService miembroEquipoService;

    @GetMapping
    public ResponseEntity<List<MiembroEquipo>> getAllMiembros() {
        List<MiembroEquipo> miembros = miembroEquipoService.getAllMiembrosEquipo();
        if (miembros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(miembros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MiembroEquipo> getMiembroById(@PathVariable Long id) {
        MiembroEquipo miembro = miembroEquipoService.findById(id);
        try {
            return ResponseEntity.ok(miembro);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MiembroEquipo> agregarMiembro(@RequestBody MiembroEquipo miembroEquipo) {
        MiembroEquipo nuevoMiembro = miembroEquipoService.saveMiembroEquipo(miembroEquipo);
        return new ResponseEntity<>(nuevoMiembro, HttpStatus.CREATED);
    }

    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<List<MiembroEquipo>> getMiembrosByEquipo(@PathVariable Long idEquipo) {
        List<MiembroEquipo> miembros = miembroEquipoService.findByIdEquipo(idEquipo);
        if (miembros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(miembros);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<MiembroEquipo>> getMiembrosByUsuario(@PathVariable Long idUsuario) {
        List<MiembroEquipo> miembros = miembroEquipoService.findByUsuario(idUsuario);
        if (miembros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(miembros);
}
}