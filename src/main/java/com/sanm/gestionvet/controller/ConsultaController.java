package com.sanm.gestionvet.controller;

import com.sanm.gestionvet.model.Consulta;
import com.sanm.gestionvet.service.ConsultaService;
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

@Tag(name = "Consultas", description = "Operaciones relacionadas con las consultas (une dueño, mascota y servicio)")
@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    // GET (lista): cada elemento trae su link "self" y la lista trae el suyo
    @Operation(summary = "Obtiene todas las consultas", description = "Retorna la lista completa con sus links HATEOAS")
    @GetMapping
    public CollectionModel<EntityModel<Consulta>> getConsultas() {
        List<EntityModel<Consulta>> lista = consultaService.getConsultas()
                .stream()
                .map(this::toModel)
                .toList();

        return CollectionModel.of(lista,
                linkTo(methodOn(ConsultaController.class).getConsultas()).withSelfRel());
    }

    // GET (por id): retorna el objeto con sus links HATEOAS
    @Operation(summary = "Obtiene una consulta por su identificador")
    @GetMapping("/{id}")
    public EntityModel<Consulta> getConsulta(@PathVariable Integer id) {
        Consulta consulta = consultaService.getConsulta(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Consulta no encontrada"));

        return toModel(consulta);
    }

    @Operation(summary = "Registra una nueva consulta")
    @PostMapping
    public Consulta saveConsulta(@RequestBody Consulta consulta) {
        return consultaService.saveConsulta(consulta);
    }

    @Operation(summary = "Actualiza una consulta existente")
    @PutMapping("/{id}")
    public Consulta updateConsulta(@PathVariable Integer id,
                         @RequestBody Consulta consulta) {
        consulta.setId(id);
        return consultaService.updateConsulta(consulta);
    }

    @Operation(summary = "Elimina una consulta")
    @DeleteMapping("/{id}")
    public void deleteConsulta(@PathVariable Integer id) {
        consultaService.deleteConsulta(id);
    }

//** - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - **//

    // Arma el EntityModel (objeto + links)
    private EntityModel<Consulta> toModel(Consulta consulta) {
        EntityModel<Consulta> model = EntityModel.of(consulta);

        model.add(linkTo(methodOn(ConsultaController.class)
                .getConsulta(consulta.getId())).withSelfRel());

        model.add(linkTo(methodOn(ConsultaController.class)
                .getConsultas()).withRel("consultas"));

        // Links a las clases relacionadas con la consulta
        if (consulta.getDuenio() != null) {
            model.add(linkTo(methodOn(DuenioController.class)
                    .getDuenio(consulta.getDuenio().getId())).withRel("duenio"));
        }
        if (consulta.getMascota() != null) {
            model.add(linkTo(methodOn(MascotaController.class)
                    .getMascota(consulta.getMascota().getId())).withRel("mascota"));
        }
        if (consulta.getServicio() != null) {
            model.add(linkTo(methodOn(ServicioController.class)
                    .getServicio(consulta.getServicio().getId())).withRel("servicio"));
        }

        return model;
    }
}
