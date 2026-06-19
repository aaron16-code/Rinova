package cl.duoc.pedido_service.controller;

import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.model.Pedido;
import cl.duoc.pedido_service.service.PedidoService;
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
@RequestMapping("api/v1/pedidos")
@Tag(name = "Pedidos", description = "Operaciones CRUD para gestión de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(summary = "Listar todos los pedidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @Operation(summary = "Buscar pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "204", description = "Pedido no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.findById(id);
        if (pedido == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pedido);
    }

    @Operation(summary = "Registrar nuevo pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.save(pedido);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar pedido por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pedido no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.update(id, pedido);
        if (pedidoActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedidoActualizado);
    }

    @Operation(summary = "Buscar pedidos por usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.findByUsuarioId(usuarioId));
    }

    @Operation(summary = "Buscar pedidos por estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pedidoService.findByEstado(estado));
    }

    @Operation(summary = "Buscar pedidos por libro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping("/libro/{libroId}")
    public ResponseEntity<?> buscarPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(pedidoService.findByLibroId(libroId));
    }

    @Operation(summary = "Listar pedidos ordenados por fecha más reciente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos obtenida correctamente")
    })
    @GetMapping("/recientes")
    public ResponseEntity<?> listarPorFecha() {
        return ResponseEntity.ok(pedidoService.findAllOrdenados());
    }
}