package cl.duoc.notificacion_service.controller;

import cl.duoc.notificacion_service.dto.NotificacionDTO;
import cl.duoc.notificacion_service.model.Notificacion;
import cl.duoc.notificacion_service.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        NotificacionDTO notificacion = notificacionService.findById(id);
        if (notificacion == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(notificacion);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Notificacion notificacion) {
        Notificacion nueva = notificacionService.save(notificacion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        notificacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Notificacion notificacion) {
        Notificacion actualizada = notificacionService.update(id, notificacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/leidas/{leida}")
    public ResponseEntity<?> buscarPorLeida(@PathVariable Boolean leida) {
        return ResponseEntity.ok(notificacionService.findByLeida(leida));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(notificacionService.findByTipo(tipo));
    }
}
