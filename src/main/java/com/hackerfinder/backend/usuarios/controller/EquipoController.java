package com.hackerfinder.backend.usuarios.controller;

import com.hackerfinder.backend.usuarios.model.MiembroEquipo;
import com.hackerfinder.backend.usuarios.model.Equipo;
import com.hackerfinder.backend.usuarios.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import java.util.Map;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @PostMapping("/{idEquipo}/miembros")
    public ResponseEntity<?> agregarMiembro(@PathVariable Long idEquipo, @RequestBody Map<String, Object> payload) {
        try {
            Long idUsuario = Long.valueOf(payload.get("idUsuario").toString());
            String rol = (String) payload.get("rol");
            MiembroEquipo miembro = equipoService.agregarMiembro(idEquipo, idUsuario, rol);
            return ResponseEntity.ok(miembro);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoService.saveEquipo(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEquipo);
    }

    //GET

    @GetMapping
    public ResponseEntity<List<Equipo>> listarEquipos() {
        List<Equipo> equipos = equipoService.listarEquipos();
        if (equipos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Long id) {
        try {
            Equipo equipo = equipoService.findById(id);
            if (equipo != null) {
                return ResponseEntity.ok(equipo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/nombre/{nombreEquipo}")
    public ResponseEntity<Equipo> obtenerEquipoPorNombre(@PathVariable String nombreEquipo) {
        try {
            Equipo equipo = equipoService.findByNombreEquipo(nombreEquipo);
            if (equipo != null) {
                return ResponseEntity.ok(equipo);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
}