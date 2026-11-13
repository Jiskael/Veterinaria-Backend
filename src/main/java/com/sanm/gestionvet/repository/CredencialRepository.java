package com.sanm.gestionvet.repository;

import com.sanm.gestionvet.model.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Integer> {

    Optional<Credencial> findByEmail(String email);
}
