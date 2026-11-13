package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Duenio;
import com.sanm.gestionvet.service.DuenioService;
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

@Tag(name = "Dueños", description = "Operaciones relacionadas con los dueños de mascotas")
@RestController
@RequestMapping("/api/v1/duenios")
public class DuenioController {

    @Autowired
    private DuenioService duenioService;

    // GET (lista): cada elemento trae su link "self" y la lista trae el suyo
    @Operation(summary = "Obtiene todos los dueños", description = "Retorna la lista completa con sus links HATEOAS")
    @GetMapping
    public CollectionModel<EntityModel<Duenio>> getDuenios() {
        List<EntityModel<Duenio>> lista = duenioService.getDuenios()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(lista,
                linkTo(methodOn(DuenioController.class).getDuenios()).withSelfRel());
    }

    // GET (por id): retorna el objeto con sus links HATEOAS
    @Operation(summary = "Obtiene un dueño por su identificador")
    @GetMapping("/{id}")
    public EntityModel<Duenio> getDuenio(@PathVariable Integer id) {
        Duenio duenio = duenioService.getDuenio(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Dueño no encontrado"));

        return toModel(duenio);
    }

    @Operation(summary = "Registra un nuevo dueño")
    @PostMapping
    public Duenio saveDuenio(@RequestBody Duenio duenio) {
        return duenioService.saveDuenio(duenio);
    }

    @Operation(summary = "Actualiza un dueño existente")
    @PutMapping("/{id}")
    public Duenio updateDuenio(@PathVariable Integer id,
                         @RequestBody Duenio duenio) {
        duenio.setId(id);
        return duenioService.updateDuenio(duenio);
    }

    @Operation(summary = "Elimina un dueño")
    @DeleteMapping("/{id}")
    public void deleteDuenio(@PathVariable Integer id) {
        duenioService.deleteDuenio(id);
    }

//** - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - **//

    // Arma el EntityModel (objeto + links)
    private EntityModel<Duenio> toModel(Duenio duenio) {
        EntityModel<Duenio> model = EntityModel.of(duenio);

        model.add(linkTo(methodOn(DuenioController.class)
                .getDuenio(duenio.getId())).withSelfRel());

        model.add(linkTo(methodOn(DuenioController.class)
                .getDuenios()).withRel("duenios"));

        return model;
    }
}
