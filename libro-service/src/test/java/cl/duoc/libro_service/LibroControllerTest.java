package cl.duoc.libro_service;

import cl.duoc.libro_service.controller.LibroController;
import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.service.LibroService;

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

@WebMvcTest(LibroController.class)
@DisplayName("Pruebas en la capa Controller de libros")
class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LibroService libroService;

    @Autowired
    private ObjectMapper objectMapper;

    private Libro libro;
    private Libro libroSinId;

    @BeforeEach
    void setUp() {
        libro = new Libro(1L, "Cien años de soledad", "978-0-06-088328-7", "NOVELA", 1967, 15990.0, 1L);
        libroSinId = new Libro(null, "Cien años de soledad", "978-0-06-088328-7", "NOVELA", 1967, 15990.0, 1L);
    }

    @Test
    @DisplayName("GET /api/v1/libros - Debería retornar 200 OK y la lista de libros")
    void testEndpointListarTodos() throws Exception {
        when(libroService.findAll()).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/v1/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Cien años de soledad"))
                .andExpect(jsonPath("$[0].isbn").value("978-0-06-088328-7"))
                .andExpect(jsonPath("$[0].precio").value(15990.0));
    }

    @Test
    @DisplayName("POST /api/v1/libros - Debería retornar 201 CREATED y el libro creado")
    void testEndpointRegistrar() throws Exception {
        when(libroService.save(any(Libro.class))).thenReturn(libro);

        mockMvc.perform(post("/api/v1/libros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libroSinId)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.titulo").value("Cien años de soledad"));
    }

    @Test
    @DisplayName("DELETE /api/v1/libros/{id} - Debería retornar 204 NO CONTENT")
    void testEndpointEliminar() throws Exception {
        mockMvc.perform(delete("/api/v1/libros/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/v1/libros/autor/{autorId} - Debería retornar libros del autor")
    void testEndpointBuscarPorAutorId() throws Exception {
        when(libroService.findByAutorId(1L)).thenReturn(List.of(libro));

        mockMvc.perform(get("/api/v1/libros/autor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].autorId").value(1));
    }
}
