package cl.duoc.resena_service.controller;

import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.service.ResenaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(resenaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        ResenaDTO resena = resenaService.findById(id);
        if (resena == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(resena);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Resena resena) {
        Resena nueva = resenaService.save(resena);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        resenaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Resena resena) {
        Resena actualizada = resenaService.update(id, resena);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<?> buscarPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(resenaService.findByLibroId(libroId));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(resenaService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/puntuacion/{puntuacion}")
    public ResponseEntity<?> buscarPorPuntuacion(@PathVariable Integer puntuacion) {
        return ResponseEntity.ok(resenaService.findByPuntuacion(puntuacion));
    }
}
