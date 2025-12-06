package com.hackerfinder.backend.usuarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hackerfinder.backend.usuarios.repository.MiembroEquipoRepository;
import com.hackerfinder.backend.usuarios.repository.UsuarioRepository;
import com.hackerfinder.backend.usuarios.repository.EquipoRepository;
import java.util.List;
import com.hackerfinder.backend.usuarios.model.MiembroEquipo;
import com.hackerfinder.backend.usuarios.model.Usuario;
import com.hackerfinder.backend.usuarios.model.Equipo;

@Service
@Transactional
public class MiembroEquipoService {
    @Autowired
    private MiembroEquipoRepository miembroEquipoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private EquipoRepository equipoRepository;

    public List<MiembroEquipo> getAllMiembrosEquipo() {
        return miembroEquipoRepository.findAll();
    }

    public MiembroEquipo findById(Long id) {
        return miembroEquipoRepository.findById(id).orElse(null);
    }

    public MiembroEquipo saveMiembroEquipo(MiembroEquipo miembroEquipo) {
        // Cargar el usuario completo si solo viene el ID
        if (miembroEquipo.getUsuario() != null && miembroEquipo.getUsuario().getIdUsuario() != null) {
            Usuario usuarioCompleto = usuarioRepository.findById(miembroEquipo.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + miembroEquipo.getUsuario().getIdUsuario()));
            miembroEquipo.setUsuario(usuarioCompleto);
        }
        
        // Cargar el equipo completo si solo viene el ID
        if (miembroEquipo.getEquipo() != null && miembroEquipo.getEquipo().getIdEquipo() != null) {
            Equipo equipoCompleto = equipoRepository.findById(miembroEquipo.getEquipo().getIdEquipo())
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con ID: " + miembroEquipo.getEquipo().getIdEquipo()));
            miembroEquipo.setEquipo(equipoCompleto);
        }
        
        return miembroEquipoRepository.save(miembroEquipo);
    }

    public void delete(Long id) {
        miembroEquipoRepository.deleteById(id);
    }

    public List<MiembroEquipo> findByIdEquipo(Long idEquipo) {
        return miembroEquipoRepository.findByEquipoIdEquipo(idEquipo);
    }

    public List<MiembroEquipo> findByUsuario(Long idUsuario) {
        return miembroEquipoRepository.findByUsuarioIdUsuario(idUsuario);
    }
}