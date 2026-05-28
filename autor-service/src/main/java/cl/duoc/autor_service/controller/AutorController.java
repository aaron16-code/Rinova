package cl.duoc.autor_service.controller;

import cl.duoc.autor_service.dto.AutorDTO;
import cl.duoc.autor_service.model.Autor;
import cl.duoc.autor_service.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        AutorDTO autor = autorService.findById(id);
        if (autor == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Autor autor) {
        Autor nuevo = autorService.save(autor);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Autor autor
    ) {
        Autor autorActualizado = autorService.update(id, autor);
        if (autorActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(autorActualizado);
    }

    @GetMapping("/nacionalidad/{nacionalidad}")
    public ResponseEntity<?> buscarPorNacionalidad(@PathVariable String nacionalidad) {
        return ResponseEntity.ok(autorService.findByNacionalidad(nacionalidad));
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(autorService.findByNombre(nombre));
    }

    @GetMapping("/anio/{anio}")
    public ResponseEntity<?> buscarPorAnioNacimiento(@PathVariable Integer anio) {
        return ResponseEntity.ok(autorService.findByAnioNacimiento(anio));
    }

    @GetMapping("/rango-anio")
    public ResponseEntity<?> buscarPorRangoAnio(
            @RequestParam Integer desde,
            @RequestParam Integer hasta) {
        return ResponseEntity.ok(autorService.findByRangoAnio(desde, hasta));
    }
}