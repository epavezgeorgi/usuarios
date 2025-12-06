package com.hackerfinder.backend.usuarios.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import com.hackerfinder.backend.usuarios.repository.PublicacionRepository;
import com.hackerfinder.backend.usuarios.repository.UsuarioRepository;
import java.util.List;
import com.hackerfinder.backend.usuarios.model.Publicacion;
import com.hackerfinder.backend.usuarios.model.Usuario;

@Service
@Transactional
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Publicacion> getAllPublicaciones() {
        return publicacionRepository.findAll();
    }

    public Publicacion findById(Long id) {
        return publicacionRepository.findById(id).orElse(null);
    }

    public List<Publicacion> findByIdUsuario(Long idUsuario) {
        return publicacionRepository.findByUsuario_IdUsuario(idUsuario);
    }

    public Publicacion savePublicacion(Publicacion publicacion) {
        // Si se envió solo el ID del usuario, cargar el usuario completo de la BD
        if (publicacion.getUsuario() != null && publicacion.getUsuario().getIdUsuario() != null) {
            Usuario usuarioCompleto = usuarioRepository.findById(publicacion.getUsuario().getIdUsuario())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + publicacion.getUsuario().getIdUsuario()));
            publicacion.setUsuario(usuarioCompleto);
        }
        return publicacionRepository.save(publicacion);
    }

    public void delete(Long id) {
        publicacionRepository.deleteById(id);
    }
}
