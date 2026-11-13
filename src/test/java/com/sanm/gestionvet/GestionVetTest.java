package com.sanm.gestionvet;

import com.sanm.gestionvet.model.Consulta;
import com.sanm.gestionvet.model.Duenio;
import com.sanm.gestionvet.model.Mascota;
import com.sanm.gestionvet.model.Servicio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Pruebas de los modelos (sin base de datos)
public class GestionVetTest {

    @Test
    void registrarDuenio() {
        // ARRANGE (preparar)
        Duenio duenio = DatosFalsos.duenio();
        duenio.setNombre("María");

        // ACT (ejecutar)
        System.out.println(duenio);

        // ASSERT (verificar)
        assertNotNull(duenio);
        assertEquals("María", duenio.getNombre());
        assertNotNull(duenio.getRut());
    }

    @Test
    void registrarMascota() {
        Mascota mascota = DatosFalsos.mascota();
        mascota.setEspecie("Perro");

        System.out.println(mascota);

        assertNotNull(mascota.getNombre());
        assertEquals("Perro", mascota.getEspecie());
    }

    @Test
    void registrarServicio() {
        Servicio servicio = DatosFalsos.servicio();
        servicio.setTitulo("Vacunación");

        System.out.println(servicio);

        assertEquals("Vacunación", servicio.getTitulo());
        assertTrue(servicio.getPrecio() > 0);
    }

    @Test
    void consultaRelacionaDuenioMascotaYServicio() {
        Consulta consulta = DatosFalsos.consulta();
        consulta.setMotivo("Control anual");

        System.out.println(consulta.getMotivo());

        assertEquals("Control anual", consulta.getMotivo());
        assertNotNull(consulta.getDuenio());
        assertNotNull(consulta.getMascota());
        assertNotNull(consulta.getServicio());
    }
}
