package cl.duoc.libro_service.controller;

import cl.duoc.libro_service.dto.LibroDTO;
import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.service.LibroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/libros")
@Tag(name = "Libros", description = "Operaciones CRUD para gestión de libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @Operation(summary = "Listar todos los libros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @Operation(summary = "Buscar libro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro encontrado"),
            @ApiResponse(responseCode = "204", description = "Libro no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        LibroDTO libro = libroService.findById(id);
        if (libro == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(libro);
    }

    @Operation(summary = "Registrar nuevo libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Libro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Libro libro) {
        Libro nuevo = libroService.save(libro);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar libro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Libro eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar libro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Libro actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Libro no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        Libro libroActualizado = libroService.update(id, libro);
        if (libroActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(libroActualizado);
    }

    @Operation(summary = "Buscar libros por género")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida correctamente")
    })
    @GetMapping("/genero/{generoId}")
    public ResponseEntity<?> buscarPorGenero(@PathVariable String generoId) {
        return ResponseEntity.ok(libroService.findByGeneroId(generoId));
    }

    @Operation(summary = "Buscar libros por autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida correctamente")
    })
    @GetMapping("/autor/{autorId}")
    public ResponseEntity<?> buscarPorAutor(@PathVariable Long autorId) {
        return ResponseEntity.ok(libroService.findByAutorId(autorId));
    }

    @Operation(summary = "Buscar libros por título")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida correctamente")
    })
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }

    @Operation(summary = "Buscar libros por rango de precio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de libros obtenida correctamente")
    })
    @GetMapping("/precio")
    public ResponseEntity<?> buscarPorRangoPrecio(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(libroService.findByPrecioBetween(min, max));
    }
}