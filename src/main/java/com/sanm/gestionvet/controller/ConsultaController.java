package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sanm.gestionvet.service.ConsultaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public List<Consulta> getConsultas() {
        return consultaService.getConsultas();
    }

    @GetMapping("/{id}")
    public Optional<Consulta> getConsulta(@PathVariable Integer id) {
        return consultaService.getConsulta(id);
    }

    @PostMapping
    public Consulta saveConsulta(@RequestBody Consulta consulta) {
        return consultaService.saveConsulta(consulta);
    }

    @PutMapping("/{id}")
    public Consulta updateConsulta(@PathVariable Integer id,@RequestBody Consulta consulta) {
        consulta.setId(id);
        return consultaService.updateConsulta(consulta);
    }

    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable Integer id) {
        consultaService.deleteConsulta(id);
    }
}
