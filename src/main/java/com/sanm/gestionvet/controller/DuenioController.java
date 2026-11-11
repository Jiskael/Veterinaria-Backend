package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Duenio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sanm.gestionvet.service.DuenioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/duenios")
public class DuenioController {

    @Autowired
    private DuenioService duenioService;

    @GetMapping
    public List<Duenio> getDuenios() {
        return duenioService.getDuenios();
    }

    @GetMapping("/{id}")
    public Optional<Duenio> getDuenio(@PathVariable Integer id) {
        return duenioService.getDuenio(id);
    }

    @PostMapping
    public Duenio saveDuenio(@RequestBody Duenio duenio) {
        return duenioService.saveDuenio(duenio);
    }

    @PutMapping("/{id}")
    public Duenio updateDuenio(@PathVariable Integer id,@RequestBody Duenio duenio) {
        duenio.setId(id);
        return duenioService.updateDuenio(duenio);
    }

    @DeleteMapping("/{id}")
    public void deleteDuenio(@PathVariable Integer id) {
        duenioService.deleteDuenio(id);
    }
}
