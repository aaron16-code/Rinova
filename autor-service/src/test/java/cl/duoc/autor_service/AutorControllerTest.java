package cl.duoc.autor_service;

import cl.duoc.autor_service.controller.AutorController;
import cl.duoc.autor_service.model.Autor;
import cl.duoc.autor_service.service.AutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AutorController.class)
@DisplayName("Pruebas en la capa Controller de autores")
class AutorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AutorService autorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Autor autor;
    private Autor autorSinId;

    @BeforeEach
    void setUp() {
        autor = new Autor(1L, "Gabriel García Márquez", "Colombiana", 1927);
        autorSinId = new Autor(null, "Gabriel García Márquez", "Colombiana", 1927);
    }

    @Test
    @DisplayName("GET /api/v1/autores - Debería retornar 200 OK y la lista de autores")
    void testEndpointListarTodos() throws Exception {
        when(autorService.findAll()).thenReturn(List.of(autor));

        mockMvc.perform(get("/api/v1/autores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Gabriel García Márquez"))
                .andExpect(jsonPath("$[0].nacionalidad").value("Colombiana"))
                .andExpect(jsonPath("$[0].anioNacimiento").value(1927));
    }

    @Test
    @DisplayName("POST /api/v1/autores - Debería retornar 201 CREATED y el autor creado")
    void testEndpointRegistrar() throws Exception {
        when(autorService.save(any(Autor.class))).thenReturn(autor);

        mockMvc.perform(post("/api/v1/autores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(autorSinId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Gabriel García Márquez"));
    }

    @Test
    @DisplayName("DELETE /api/v1/autores/{id} - Debería retornar 204 NO CONTENT")
    void testEndpointEliminar() throws Exception {
        mockMvc.perform(delete("/api/v1/autores/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/v1/autores/nacionalidad/{nacionalidad} - Debería retornar lista filtrada")
    void testEndpointBuscarPorNacionalidad() throws Exception {
        when(autorService.findByNacionalidad("Colombiana")).thenReturn(List.of(autor));

        mockMvc.perform(get("/api/v1/autores/nacionalidad/Colombiana"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nacionalidad").value("Colombiana"));
    }
}
