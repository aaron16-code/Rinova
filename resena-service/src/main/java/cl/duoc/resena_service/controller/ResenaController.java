package cl.duoc.resena_service.controller;

import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.service.ResenaService;
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
@RequestMapping("/api/v1/resenas")
@Tag(name = "Reseñas", description = "Operaciones CRUD para gestión de reseñas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @Operation(summary = "Listar todas las reseñas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reseñas obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(resenaService.findAll());
    }

    @Operation(summary = "Buscar reseña por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña encontrada"),
            @ApiResponse(responseCode = "204", description = "Reseña no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        ResenaDTO resena = resenaService.findById(id);
        if (resena == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(resena);
    }

    @Operation(summary = "Registrar nueva reseña")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Resena resena) {
        Resena nueva = resenaService.save(resena);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar reseña por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reseña eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        resenaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar reseña por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Resena resena) {
        Resena actualizada = resenaService.update(id, resena);
        if (actualizada == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizada);
    }

    @Operation(summary = "Buscar reseñas por libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reseñas obtenida correctamente")
    })
    @GetMapping("/libro/{libroId}")
    public ResponseEntity<?> buscarPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(resenaService.findByLibroId(libroId));
    }

    @Operation(summary = "Buscar reseñas por usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reseñas obtenida correctamente")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(resenaService.findByUsuarioId(usuarioId));
    }

    @Operation(summary = "Buscar reseñas por puntuación")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de reseñas obtenida correctamente")
    })
    @GetMapping("/puntuacion/{puntuacion}")
    public ResponseEntity<?> buscarPorPuntuacion(@PathVariable Integer puntuacion) {
        return ResponseEntity.ok(resenaService.findByPuntuacion(puntuacion));
    }
}