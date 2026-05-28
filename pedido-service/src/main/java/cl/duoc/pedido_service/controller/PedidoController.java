package cl.duoc.pedido_service.controller;

import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.model.Pedido;
import cl.duoc.pedido_service.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        PedidoDTO pedido = pedidoService.findById(id);
        if (pedido == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(pedido);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Pedido pedido) {
        Pedido nuevo = pedidoService.save(pedido);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Pedido pedido
    ) {
        Pedido pedidoActualizado = pedidoService.update(id, pedido);
        if (pedidoActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pedidoActualizado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.findByUsuarioId(usuarioId));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<?> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(pedidoService.findByEstado(estado));
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<?> buscarPorLibro(@PathVariable Long libroId) {
        return ResponseEntity.ok(pedidoService.findByLibroId(libroId));
    }

    @GetMapping("/recientes")
    public ResponseEntity<?> listarPorFecha() {
        return ResponseEntity.ok(pedidoService.findAllOrdenados());
    }
}