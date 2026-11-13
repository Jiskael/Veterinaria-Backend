package com.sanm.gestionvet.service;

import com.sanm.gestionvet.DatosFalsos;
import com.sanm.gestionvet.model.Duenio;
import com.sanm.gestionvet.repository.DuenioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// El repository es un "mock" (falso): el test no necesita MySQL
@ExtendWith(MockitoExtension.class)
class DuenioServiceTest {

    @Mock
    private DuenioRepository duenioRepository;

    @InjectMocks
    private DuenioService duenioService;

    @Test
    void guardarDuenio() {
        Duenio duenio = DatosFalsos.duenio();
        when(duenioRepository.save(duenio)).thenReturn(duenio);

        Duenio guardado = duenioService.saveDuenio(duenio);

        assertEquals(duenio.getRut(), guardado.getRut());
        verify(duenioRepository).save(duenio);
    }

    @Test
    void listarDuenios() {
        when(duenioRepository.findAll())
                .thenReturn(List.of(DatosFalsos.duenio(), DatosFalsos.duenio()));

        List<Duenio> duenios = duenioService.getDuenios();

        assertEquals(2, duenios.size());
    }

    @Test
    void buscarDuenioPorId() {
        Duenio duenio = DatosFalsos.duenio();
        duenio.setId(1);
        when(duenioRepository.findById(1)).thenReturn(Optional.of(duenio));

        Optional<Duenio> resultado = duenioService.getDuenio(1);

        assertTrue(resultado.isPresent());
        assertEquals(duenio.getNombre(), resultado.get().getNombre());
    }

    @Test
    void eliminarDuenio() {
        duenioService.deleteDuenio(1);

        verify(duenioRepository).deleteById(1);
    }
}
