package com.sanm.gestionvet;

import com.sanm.gestionvet.model.Consulta;
import com.sanm.gestionvet.model.Duenio;
import com.sanm.gestionvet.model.Mascota;
import com.sanm.gestionvet.model.Servicio;
import net.datafaker.Faker;

// Genera objetos con datos falsos (DataFaker) para usar en todos los tests
public class DatosFalsos {

    private static final Faker faker = new Faker();

    public static Duenio duenio() {
        Duenio duenio = new Duenio();
        duenio.setRut(faker.number().numberBetween(10000000, 25999999)
                + "-" + faker.number().numberBetween(0, 9));
        duenio.setNombre(faker.name().firstName());
        duenio.setApellido(faker.name().lastName());
        duenio.setEmail(faker.internet().emailAddress());
        duenio.setTelefono(faker.number().numberBetween(900000000, 999999999));
        return duenio;
    }

    public static Mascota mascota() {
        Mascota mascota = new Mascota();
        mascota.setNombre(faker.dog().name());
        mascota.setEspecie(faker.animal().name());
        return mascota;
    }

    public static Servicio servicio() {
        Servicio servicio = new Servicio();
        servicio.setTitulo(faker.lorem().word());
        servicio.setDescripcion(faker.lorem().sentence());
        servicio.setPrecio(faker.number().numberBetween(5000, 90000));
        return servicio;
    }

    public static Consulta consulta() {
        Consulta consulta = new Consulta();
        consulta.setMotivo(faker.lorem().sentence());
        consulta.setDuenio(duenio());
        consulta.setMascota(mascota());
        consulta.setServicio(servicio());
        return consulta;
    }
}
