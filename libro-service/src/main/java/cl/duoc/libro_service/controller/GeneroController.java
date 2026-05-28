package cl.duoc.libro_service.controller;

import cl.duoc.libro_service.model.Genero;
import cl.duoc.libro_service.service.GeneroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/generos")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(generoService.findById(codigo));
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Genero genero) {
        Genero nuevo = generoService.save(genero);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo, @Valid @RequestBody Genero genero) {
        return ResponseEntity.ok(generoService.update(codigo, genero));
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> borrar(@PathVariable String codigo) {
        generoService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}