package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sanm.gestionvet.service.MascotaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<Mascota> getMascotas() {return mascotaService.getMascotas();}

    @GetMapping("/{id}")
    public Optional<Mascota> getMascota(@PathVariable Integer id) {
        return mascotaService.getMascota(id);
    }

    @PostMapping
    public Mascota saveMascota(@RequestBody Mascota mascota) {
        return mascotaService.saveMascota(mascota);
    }

    @PutMapping("/{id}")
    public Mascota updateMascota(@PathVariable Integer id,@RequestBody Mascota mascota) {
        mascota.setId(id);
        return mascotaService.updateMascota(mascota);
    }

    @DeleteMapping("/{id}")
    public void deleteMascota(@PathVariable Integer id) {
        mascotaService.deleteMascota(id);
    }
}
