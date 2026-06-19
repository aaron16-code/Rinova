package cl.duoc.pago_service.controller;

import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.model.EstadoPago;
import cl.duoc.pago_service.model.MetodoPago;
import cl.duoc.pago_service.model.Pago;
import cl.duoc.pago_service.service.PagoService;
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
@RequestMapping("api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones CRUD para gestión de pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Operation(summary = "Listar todos los pagos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @Operation(summary = "Buscar pago por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago encontrado"),
            @ApiResponse(responseCode = "204", description = "Pago no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        PagoDTO pago = pagoService.findById(id);
        if (pago == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pago);
    }

    @Operation(summary = "Registrar nuevo pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pago creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Pago pago) {
        Pago nuevo = pagoService.save(pago);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar pago por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pago eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar pago por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Pago no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Pago pago) {
        Pago actualizado = pagoService.update(id, pago);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Buscar pagos por pedido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    })
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> buscarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pagoService.findByPedidoId(pedidoId));
    }

    @Operation(summary = "Buscar pagos por estado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    })
    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable EstadoPago estado) {
        return ResponseEntity.ok(pagoService.findByEstado(estado));
    }

    @Operation(summary = "Buscar pagos por método de pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de pagos obtenida correctamente")
    })
    @GetMapping("/metodo/{metodoPago}")
    public ResponseEntity<?> buscarPorMetodoPago(@PathVariable MetodoPago metodoPago) {
        return ResponseEntity.ok(pagoService.findByMetodoPago(metodoPago));
    }
}