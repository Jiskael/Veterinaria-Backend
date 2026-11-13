package com.sanm.gestionvet.service;

import com.sanm.gestionvet.DatosFalsos;
import com.sanm.gestionvet.model.Consulta;
import com.sanm.gestionvet.repository.ConsultaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaServiceTest {

    @Mock
    private ConsultaRepository consultaRepository;

    @InjectMocks
    private ConsultaService consultaService;

    @Test
    void guardarConsultaConSusRelaciones() {
        Consulta consulta = DatosFalsos.consulta();
        when(consultaRepository.save(consulta)).thenReturn(consulta);

        Consulta guardada = consultaService.saveConsulta(consulta);

        assertNotNull(guardada.getDuenio());
        assertNotNull(guardada.getMascota());
        assertNotNull(guardada.getServicio());
        assertEquals(consulta.getMotivo(), guardada.getMotivo());
    }

    @Test
    void listarConsultas() {
        when(consultaRepository.findAll())
                .thenReturn(List.of(DatosFalsos.consulta(), DatosFalsos.consulta(), DatosFalsos.consulta()));

        assertEquals(3, consultaService.getConsultas().size());
    }

    @Test
    void eliminarConsulta() {
        consultaService.deleteConsulta(5);

        verify(consultaRepository).deleteById(5);
    }
}
