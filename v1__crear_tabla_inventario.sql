package cl.duoc.inventario_service.controller;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(inventarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        InventarioDTO inventario = inventarioService.findById(id);
        if (inventario == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(inventario);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Inventario inventario) {
        Inventario nuevo = inventarioService.save(inventario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        inventarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Inventario inventario
    ) {
        Inventario inventarioActualizado = inventarioService.update(id, inventario);
        if (inventarioActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(inventarioActualizado);
    }

    @GetMapping("/libro/{libroId}")
    public ResponseEntity<?> buscarPorLibro(@PathVariable Long libroId) {
        InventarioDTO inventario = inventarioService.findByLibroId(libroId);
        if (inventario == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(inventario);
    }

    @GetMapping("/bajo-minimo")
    public ResponseEntity<?> listarBajoMinimo() {
        return ResponseEntity.ok(inventarioService.findStockBajoMinimo());
    }

    @GetMapping("/stock/{cantidad}")
    public ResponseEntity<?> buscarPorStockMenorA(@PathVariable Integer cantidad) {
        return ResponseEntity.ok(inventarioService.findByStockMenorA(cantidad));
    }
}