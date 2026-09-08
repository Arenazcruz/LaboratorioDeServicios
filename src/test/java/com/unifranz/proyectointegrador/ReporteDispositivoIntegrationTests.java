package com.unifranz.proyectointegrador;

import com.unifranz.proyectointegrador.domain.Dispositivo;
import com.unifranz.proyectointegrador.infrastructure.persistence.DispositivoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class ReporteDispositivoIntegrationTests {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DispositivoRepository dispositivoRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void configurarMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void reportePorMarcaIgnoraMayusculasYExcluyeDesactivadosYOtrasMarcas() throws Exception {
        Dispositivo activo = guardarDispositivo("Galaxy", "Samsung", true);
        guardarDispositivo("Galaxy anterior", "Samsung", false);
        guardarDispositivo("Otro equipo", "Motorola", true);

        mockMvc.perform(get("/dispositivos/marca/sAmSuNg"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(activo.getId().intValue()))
                .andExpect(jsonPath("$[0].nombre").value("Galaxy"))
                .andExpect(jsonPath("$[0].marca").value("Samsung"))
                .andExpect(jsonPath("$[0].tipo").value("Celular"))
                .andExpect(jsonPath("$[0].precio").value(1500.0))
                .andExpect(jsonPath("$[0].estado").doesNotExist());
    }

    @Test
    void reporteDesactivadosIncluyeTodasLasMarcasYExcluyeActivos() throws Exception {
        guardarDispositivo("Equipo A", "Samsung", false);
        guardarDispositivo("Equipo B", "Motorola", false);
        guardarDispositivo("Equipo activo", "Samsung", true);

        mockMvc.perform(get("/dispositivos/desactivados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[*].nombre", org.hamcrest.Matchers.containsInAnyOrder(
                        "Equipo A", "Equipo B")));
    }

    @Test
    void reportesSinCoincidenciasDevuelvenListasVacias() throws Exception {
        guardarDispositivo("Equipo activo", "Samsung", true);

        mockMvc.perform(get("/dispositivos/marca/Inexistente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());

        mockMvc.perform(get("/dispositivos/desactivados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    private Dispositivo guardarDispositivo(String nombre, String marca, boolean estado) {
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setNombre(nombre);
        dispositivo.setMarca(marca);
        dispositivo.setTipo("Celular");
        dispositivo.setPrecio(1500.0);
        dispositivo.setEstado(estado);
        return dispositivoRepository.saveAndFlush(dispositivo);
    }
}
