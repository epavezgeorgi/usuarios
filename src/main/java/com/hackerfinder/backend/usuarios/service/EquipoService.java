package com.hackerfinder.backend.usuarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hackerfinder.backend.usuarios.repository.EquipoRepository;
import java.util.List;
import com.hackerfinder.backend.usuarios.model.Equipo;

import com.hackerfinder.backend.usuarios.repository.MiembroEquipoRepository;
import com.hackerfinder.backend.usuarios.repository.UsuarioRepository;
import com.hackerfinder.backend.usuarios.model.MiembroEquipo;
import com.hackerfinder.backend.usuarios.model.Usuario;

@Service
@Transactional
public class EquipoService {
    
    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MiembroEquipoRepository miembroEquipoRepository;

    public List<Equipo> listarEquipos() {
        return equipoRepository.findAll();
    }

    public Equipo findById(Long id) {
        return equipoRepository.findById(id).orElse(null);
    }

    public Equipo findByNombreEquipo(String nombreEquipo) {
        return equipoRepository.findByNombreEquipo(nombreEquipo);
    }

    public String findLogoByIdEquipo(Long idEquipo) {
        return equipoRepository.findLogoByIdEquipo(idEquipo);
    }

    public Equipo saveEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public MiembroEquipo agregarMiembro(Long idEquipo, Long idUsuario, String rol) {
        Equipo equipo = equipoRepository.findById(idEquipo).orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        MiembroEquipo miembro = new MiembroEquipo();
        miembro.setEquipo(equipo);
        miembro.setUsuario(usuario);
        miembro.setRol(rol);

        return miembroEquipoRepository.save(miembro);
    }

}
