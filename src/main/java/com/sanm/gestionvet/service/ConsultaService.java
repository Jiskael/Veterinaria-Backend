package com.sanm.gestionvet.service;

import com.sanm.gestionvet.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sanm.gestionvet.repository.ConsultaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> getConsultas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> getConsulta(Integer id) {
        return consultaRepository.findById(id);
    }

    public Consulta saveConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public Consulta updateConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public void deleteConsulta(Integer id) {
        consultaRepository.deleteById(id);
    }

}
