package com.sanm.gestionvet.service;

import com.sanm.gestionvet.model.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sanm.gestionvet.repository.MascotaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<Mascota> getMascotas() {
        return mascotaRepository.findAll();
    }

    public Optional<Mascota> getMascota(Integer id) {
        return mascotaRepository.findById(id);
    }

    public Mascota saveMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota updateMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public void deleteMascota(Integer id) {
        mascotaRepository.deleteById(id);
    }
}
