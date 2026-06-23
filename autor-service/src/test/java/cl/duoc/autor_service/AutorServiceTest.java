package cl.duoc.autor_service;

import cl.duoc.autor_service.exception.ResourceNotFoundException;
import cl.duoc.autor_service.mapper.AutorMapper;
import cl.duoc.autor_service.model.Autor;
import cl.duoc.autor_service.repository.AutorRepository;
import cl.duoc.autor_service.service.AutorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para AutorService")
public class AutorServiceTest {

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AutorMapper autorMapper;

    @InjectMocks
    private AutorService autorService;

    private Autor autor;

    @BeforeEach
    public void setUp() {
        autor = new Autor(1L, "Gabriel García Márquez", "Colombiana", 1927);
    }

    @Test
    @DisplayName("Debe listar todos los autores correctamente")
    public void findAll_deberiaRetornarListaDeAutores() {
        when(autorRepository.findAll()).thenReturn(List.of(autor));

        List<Autor> resultado = autorService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Gabriel García Márquez", resultado.get(0).getNombre());

        verify(autorRepository).findAll();
    }

    @Test
    @DisplayName("Debe lanzar ResourceNotFoundException cuando el autor no existe")
    public void findById_cuandoNoExiste_deberiaLanzarException() {
        when(autorRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> autorService.findById(99L)
        );

        assertTrue(exception.getMessage().contains("99"));
        verify(autorRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un autor correctamente")
    public void save_deberiaGuardarYRetornarAutor() {
        when(autorRepository.save(autor)).thenReturn(autor);

        Autor resultado = autorService.save(autor);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Gabriel García Márquez", resultado.getNombre());

        verify(autorRepository).save(autor);
    }

    @Test
    @DisplayName("Debe eliminar un autor por ID correctamente")
    public void delete_deberiaEliminarAutorPorId() {
        autorService.delete(1L);
        verify(autorRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe actualizar un autor cuando existe")
    public void update_cuandoExiste_deberiaActualizarYRetornarAutor() {
        Autor datosNuevos = new Autor(null, "Actualizado", "Chilena", 1980);
        when(autorRepository.findById(1L)).thenReturn(Optional.of(autor));
        when(autorRepository.save(any(Autor.class))).thenReturn(autor);

        Autor resultado = autorService.update(1L, datosNuevos);

        assertNotNull(resultado);
        verify(autorRepository).findById(1L);
        verify(autorRepository).save(autor);
    }

    @Test
    @DisplayName("Debe lanzar exception al actualizar autor inexistente")
    public void update_cuandoNoExiste_deberiaLanzarException() {
        when(autorRepository.findById(99L)).thenReturn(Optional.empty());
        Autor datosNuevos = new Autor(null, "Test", "Chilena", 1990);

        assertThrows(
                ResourceNotFoundException.class,
                () -> autorService.update(99L, datosNuevos)
        );
        verify(autorRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe retornar autores filtrados por nacionalidad")
    public void findByNacionalidad_deberiaRetornarFiltrados() {
        when(autorRepository.findByNacionalidad("Colombiana")).thenReturn(List.of(autor));

        List<Autor> resultado = autorService.findByNacionalidad("Colombiana");

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Colombiana", resultado.get(0).getNacionalidad());

        verify(autorRepository).findByNacionalidad("Colombiana");
    }

    @Test
    @DisplayName("Debe retornar autores por año de nacimiento")
    public void findByAnioNacimiento_deberiaRetornarAutoresDelAnio() {
        when(autorRepository.findByAnioNacimiento(1927)).thenReturn(List.of(autor));

        List<Autor> resultado = autorService.findByAnioNacimiento(1927);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1927, resultado.get(0).getAnioNacimiento());

        verify(autorRepository).findByAnioNacimiento(1927);
    }
}
