package com.hackerfinder.backend.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hackerfinder.backend.usuarios.model.MiembroEquipo;
import java.util.List;

@Repository
public interface MiembroEquipoRepository extends JpaRepository<MiembroEquipo, Long> {
    public List<MiembroEquipo> findByEquipoIdEquipo(Long idEquipo);
    public List<MiembroEquipo> findByUsuarioIdUsuario(Long idUsuario);

    public boolean existsByEquipoIdEquipoAndUsuarioIdUsuario(Long idEquipo, Long idUsuario);
    
    public long countByEquipoIdEquipo(Long idEquipo);

}