package com.sanm.gestionvet.service;

import com.sanm.gestionvet.model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sanm.gestionvet.repository.ServicioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    public List<Servicio> getServicios() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> getServicio(Integer id) {
        return servicioRepository.findById(id);
    }

    public Servicio saveServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio updateServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public void deleteServicio(Integer id) {
        servicioRepository.deleteById(id);
    }
}
