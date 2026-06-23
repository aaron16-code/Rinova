package cl.duoc.libro_service;

import cl.duoc.libro_service.exception.ResourceNotFoundException;
import cl.duoc.libro_service.mapper.LibroMapper;
import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.repository.GeneroRepository;
import cl.duoc.libro_service.repository.LibroRepository;
import cl.duoc.libro_service.service.LibroService;

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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LibroService")
public class LibroServiceTest {

    @Mock
    private LibroRepository libroRepository;

    @Mock
    private GeneroRepository generoRepository;

    @Mock
    private LibroMapper libroMapper;

    @InjectMocks
    private LibroService libroService;

    private Libro libro;

    @BeforeEach
    public void setUp() {
        libro = new Libro(1L, "Cien años de soledad", "978-0-06-088328-7", "NOVELA", 1967, 15990.0, 1L);
    }

    @Test
    @DisplayName("Debe listar todos los libros correctamente")
    public void findAll_deberiaRetornarListaDeLibros() {
        when(libroRepository.findAll()).thenReturn(List.of(libro));

        List<Libro> resultado = libroService.findAll();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Cien años de soledad", resultado.get(0).getTitulo());

        verify(libroRepository).findAll();
    }

    @Test
    @DisplayName("Debe lanzar ResourceNotFoundException cuando el libro no existe")
    public void findById_cuandoNoExiste_deberiaLanzarException() {
        when(libroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> libroService.findById(99L)
        );

        verify(libroRepository).findById(99L);
    }

    @Test
    @DisplayName("Debe guardar un libro correctamente cuando el genero existe")
    public void save_cuandoGeneroExiste_deberiaGuardarLibro() {
        when(generoRepository.existsById("NOVELA")).thenReturn(true);
        when(libroRepository.save(libro)).thenReturn(libro);

        Libro resultado = libroService.save(libro);

        assertNotNull(resultado);
        assertEquals("Cien años de soledad", resultado.getTitulo());

        verify(generoRepository).existsById("NOVELA");
        verify(libroRepository).save(libro);
    }

    @Test
    @DisplayName("Debe lanzar exception al guardar con genero inexistente")
    public void save_cuandoGeneroNoExiste_deberiaLanzarException() {
        when(generoRepository.existsById("INEXISTENTE")).thenReturn(false);
        Libro libroInvalido = new Libro(null, "Titulo", "isbn", "INEXISTENTE", 2000, 9990.0, 1L);

        assertThrows(
                ResourceNotFoundException.class,
                () -> libroService.save(libroInvalido)
        );

        verify(generoRepository).existsById("INEXISTENTE");
        verify(libroRepository, never()).save(any());
    }

    @Test
    @DisplayName("Debe eliminar un libro por ID correctamente")
    public void delete_deberiaEliminarLibroPorId() {
        libroService.delete(1L);
        verify(libroRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Debe retornar libros filtrados por autorId")
    public void findByAutorId_deberiaRetornarLibrosDelAutor() {
        when(libroRepository.findByAutorId(1L)).thenReturn(List.of(libro));

        List<Libro> resultado = libroService.findByAutorId(1L);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(1L, resultado.get(0).getAutorId());

        verify(libroRepository).findByAutorId(1L);
    }

    @Test
    @DisplayName("Debe retornar libros en rango de precio")
    public void findByPrecioBetween_deberiaRetornarLibrosEnRango() {
        when(libroRepository.findByPrecioBetween(10000.0, 20000.0)).thenReturn(List.of(libro));

        List<Libro> resultado = libroService.findByPrecioBetween(10000.0, 20000.0);

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        verify(libroRepository).findByPrecioBetween(10000.0, 20000.0);
    }
}
