package com.hackerfinder.backend.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackerfinder.backend.usuarios.model.Publicacion;
import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
   List<Publicacion> findByUsuario_IdUsuario(Long idUsuario);
    public Publicacion findByIdPublicacion(Long idPublicacion);
    public Publicacion findByTitulo(String titulo);
    List<Publicacion> findByContenidoContainingIgnoreCase(String palabra);
}
