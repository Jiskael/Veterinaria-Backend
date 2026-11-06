package controller;

import model.Servicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ServicioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/servicios")
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @GetMapping
    public List<Servicio> getServicios() {return servicioService.getServicios();}

    @GetMapping("/{id}")
    public Optional<Servicio> getServicio(@PathVariable Integer id) {
        return servicioService.getServicio(id);
    }

    @PostMapping
    public Servicio saveServicio(@RequestBody Servicio servicio) {
        return servicioService.saveServicio(servicio);
    }

    @PutMapping("/{id}")
    public Servicio updateServicio(@PathVariable Integer id,@RequestBody Servicio servicio) {
        servicio.setId(id);
        return servicioService.updateServicio(servicio);
    }

    @DeleteMapping("/{id}")
    public void deleteServicio(@PathVariable Integer id) {
        servicioService.deleteServicio(id);
    }
}
