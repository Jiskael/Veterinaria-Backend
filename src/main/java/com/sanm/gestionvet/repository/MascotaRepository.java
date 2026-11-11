package com.sanm.gestionvet.repository;

import com.sanm.gestionvet.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MascotaRepository extends JpaRepository<Mascota, Integer> {
}
