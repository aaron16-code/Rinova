package cl.duoc.libro_service.controller;

import cl.duoc.libro_service.dto.LibroDTO;
import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(libroService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        LibroDTO libro = libroService.findById(id);
        if (libro == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Libro libro) {
        Libro nuevo = libroService.save(libro);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        libroService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Libro libro) {
        Libro libroActualizado = libroService.update(id, libro);
        if (libroActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(libroActualizado);
    }

    @GetMapping("/genero/{generoId}")
    public ResponseEntity<?> buscarPorGenero(@PathVariable String generoId) {
        return ResponseEntity.ok(libroService.findByGeneroId(generoId));
    }

    @GetMapping("/autor/{autorId}")
    public ResponseEntity<?> buscarPorAutor(@PathVariable Long autorId) {
        return ResponseEntity.ok(libroService.findByAutorId(autorId));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorTitulo(@RequestParam String titulo) {
        return ResponseEntity.ok(libroService.buscarPorTitulo(titulo));
    }

    @GetMapping("/precio")
    public ResponseEntity<?> buscarPorRangoPrecio(@RequestParam Double min, @RequestParam Double max) {
        return ResponseEntity.ok(libroService.findByPrecioBetween(min, max));
    }
}