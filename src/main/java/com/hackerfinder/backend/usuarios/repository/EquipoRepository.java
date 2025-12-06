package com.hackerfinder.backend.usuarios.repository;

import org.springframework.stereotype.Repository;
import com.hackerfinder.backend.usuarios.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    
    public Equipo findByNombreEquipo(String nombreEquipo);

    @Query(value = "SELECT logo_equipo FROM equipo WHERE id_equipo = ?1", nativeQuery = true)
    public String findLogoByIdEquipo(Long idEquipo);

}
