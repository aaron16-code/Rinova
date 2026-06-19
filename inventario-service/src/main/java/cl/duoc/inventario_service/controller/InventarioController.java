package cl.duoc.inventario_service.controller;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.service.InventarioService;
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
@RequestMapping("api/v1/inventario")
@Tag(name = "Inventario", description = "Operaciones CRUD para gestión de inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @Operation(summary = "Listar todo el inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inventario obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @Operation(summary = "Buscar inventario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "204", description = "Inventario no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        InventarioDTO inventario = inventarioService.findById(id);
        if (inventario == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(inventario);
    }

    @Operation(summary = "Registrar nuevo inventario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Inventario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.save(inventario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar inventario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Inventario eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar inventario por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Inventario no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Inventario inventario) {
        Inventario inventarioActualizado = inventarioService.update(id, inventario);
        if (inventarioActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventarioActualizado);
    }

    @Operation(summary = "Buscar inventario por ID de libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inventario encontrado"),
            @ApiResponse(responseCode = "204", description = "Inventario no encontrado para ese libro")
    })
    @GetMapping("/libro/{libroId}")
    public ResponseEntity<?> buscarPorLibro(@PathVariable Long libroId) {
        InventarioDTO inventario = inventarioService.findByLibroId(libroId);
        if (inventario == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(inventario);
    }

    @Operation(summary = "Listar inventario con stock bajo mínimo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inventario bajo minimo obtenida correctamente")
    })
    @GetMapping("/bajo-minimo")
    public ResponseEntity<?> listarBajoMinimo() {
        return ResponseEntity.ok(inventarioService.findStockBajoMinimo());
    }

    @Operation(summary = "Buscar inventario con stock menor a una cantidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de inventario obtenida correctamente")
    })
    @GetMapping("/stock/{cantidad}")
    public ResponseEntity<?> buscarPorStockMenorA(@PathVariable Integer cantidad) {
        return ResponseEntity.ok(inventarioService.findByStockMenorA(cantidad));
    }
}