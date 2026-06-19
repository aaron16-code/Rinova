package cl.duoc.notificacion_service.controller;

import cl.duoc.notificacion_service.dto.NotificacionDTO;
import cl.duoc.notificacion_service.model.Notificacion;
import cl.duoc.notificacion_service.service.NotificacionService;
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
@RequestMapping("api/v1/notificaciones")
@Tag(name = "Notificaciones", description = "Operaciones CRUD para gestión de notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    @Operation(summary = "Listar todas las notificaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(notificacionService.findAll());
    }

    @Operation(summary = "Buscar notificación por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación encontrada"),
            @ApiResponse(responseCode = "204", description = "Notificación no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        NotificacionDTO notificacion = notificacionService.findById(id);
        if (notificacion == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(notificacion);
    }

    @Operation(summary = "Registrar nueva notificación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificación creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Notificacion notificacion) {
        Notificacion nueva = notificacionService.save(notificacion);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar notificación por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificación eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        notificacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar notificación por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificación actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Notificación no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Notificacion notificacion) {
        Notificacion actualizada = notificacionService.update(id, notificacion);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Buscar notificaciones por usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida correctamente")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(notificacionService.findByUsuarioId(usuarioId));
    }

    @Operation(summary = "Buscar notificaciones por estado de lectura")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida correctamente")
    })
    @GetMapping("/leidas/{leida}")
    public ResponseEntity<?> buscarPorLeida(@PathVariable Boolean leida) {
        return ResponseEntity.ok(notificacionService.findByLeida(leida));
    }

    @Operation(summary = "Buscar notificaciones por tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de notificaciones obtenida correctamente")
    })
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<?> buscarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(notificacionService.findByTipo(tipo));
    }
}