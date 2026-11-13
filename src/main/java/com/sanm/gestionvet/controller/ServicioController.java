package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Servicio;
import com.sanm.gestionvet.service.ServicioService;
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

@Tag(name = "Servicios", description = "Operaciones relacionadas con los servicios de la veterinaria")
@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    // GET (lista): cada elemento trae su link "self" y la lista trae el suyo
    @Operation(summary = "Obtiene todos los servicios", description = "Retorna la lista completa con sus links HATEOAS")
    @GetMapping
    public CollectionModel<EntityModel<Servicio>> getServicios() {
        List<EntityModel<Servicio>> lista = servicioService.getServicios()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(lista,
                linkTo(methodOn(ServicioController.class).getServicios()).withSelfRel());
    }

    // GET (por id): retorna el objeto con sus links HATEOAS
    @Operation(summary = "Obtiene un servicio por su identificador")
    @GetMapping("/{id}")
    public EntityModel<Servicio> getServicio(@PathVariable Integer id) {
        Servicio servicio = servicioService.getServicio(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Servicio no encontrado"));

        return toModel(servicio);
    }

    @Operation(summary = "Registra un nuevo servicio")
    @PostMapping
    public Servicio saveServicio(@RequestBody Servicio servicio) {
        return servicioService.saveServicio(servicio);
    }

    @Operation(summary = "Actualiza un servicio existente")
    @PutMapping("/{id}")
    public Servicio updateServicio(@PathVariable Integer id,
                         @RequestBody Servicio servicio) {
        servicio.setId(id);
        return servicioService.updateServicio(servicio);
    }

    @Operation(summary = "Elimina un servicio")
    @DeleteMapping("/{id}")
    public void deleteServicio(@PathVariable Integer id) {
        servicioService.deleteServicio(id);
    }

//** - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - **//

    // Arma el EntityModel (objeto + links)
    private EntityModel<Servicio> toModel(Servicio servicio) {
        EntityModel<Servicio> model = EntityModel.of(servicio);

        model.add(linkTo(methodOn(ServicioController.class)
                .getServicio(servicio.getId())).withSelfRel());

        model.add(linkTo(methodOn(ServicioController.class)
                .getServicios()).withRel("servicios"));

        return model;
    }
}
