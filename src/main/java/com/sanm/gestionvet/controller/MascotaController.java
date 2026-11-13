package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Mascota;
import com.sanm.gestionvet.service.MascotaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "Mascotas", description = "Operaciones relacionadas con las mascotas")
@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // GET (lista): cada elemento trae su link "self" y la lista trae el suyo
    @Operation(summary = "Obtiene todas las mascotas", description = "Retorna la lista completa con sus links HATEOAS")
    @GetMapping
    public CollectionModel<EntityModel<Mascota>> getMascotas() {
        List<EntityModel<Mascota>> lista = mascotaService.getMascotas()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(lista,
                linkTo(methodOn(MascotaController.class).getMascotas()).withSelfRel());
    }

    // GET (por id): retorna el objeto con sus links HATEOAS
    @Operation(summary = "Obtiene una mascota por su identificador")
    @GetMapping("/{id}")
    public EntityModel<Mascota> getMascota(@PathVariable Integer id) {
        Mascota mascota = mascotaService.getMascota(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Mascota no encontrada"));

        return toModel(mascota);
    }

    @Operation(summary = "Registra una nueva mascota")
    @PostMapping
    public Mascota saveMascota(@RequestBody Mascota mascota) {
        return mascotaService.saveMascota(mascota);
    }

    @Operation(summary = "Actualiza una mascota existente")
    @PutMapping("/{id}")
    public Mascota updateMascota(@PathVariable Integer id,
                         @RequestBody Mascota mascota) {
        mascota.setId(id);
        return mascotaService.updateMascota(mascota);
    }

    @Operation(summary = "Elimina una mascota")
    @DeleteMapping("/{id}")
    public void deleteMascota(@PathVariable Integer id) {
        mascotaService.deleteMascota(id);
    }

//** - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - **//

    // Arma el EntityModel (objeto + links)
    private EntityModel<Mascota> toModel(Mascota mascota) {
        EntityModel<Mascota> model = EntityModel.of(mascota);

        model.add(linkTo(methodOn(MascotaController.class)
                .getMascota(mascota.getId())).withSelfRel());

        model.add(linkTo(methodOn(MascotaController.class)
                .getMascotas()).withRel("mascotas"));

        return model;
    }
}
