package cl.duoc.pago_service.controller;

import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.model.EstadoPago;
import cl.duoc.pago_service.model.MetodoPago;
import cl.duoc.pago_service.model.Pago;
import cl.duoc.pago_service.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pagoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        PagoDTO pago = pagoService.findById(id);
        if (pago == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pago);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Pago pago) {
        Pago nuevo = pagoService.save(pago);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        pagoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Pago pago) {
        Pago actualizado = pagoService.update(id, pago);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<?> buscarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pagoService.findByPedidoId(pedidoId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable EstadoPago estado) {
        return ResponseEntity.ok(pagoService.findByEstado(estado));
    }

    @GetMapping("/metodo/{metodoPago}")
    public ResponseEntity<?> buscarPorMetodoPago(@PathVariable MetodoPago metodoPago) {
        return ResponseEntity.ok(pagoService.findByMetodoPago(metodoPago));
    }
}