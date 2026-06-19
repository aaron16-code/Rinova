package cl.duoc.libro_service.controller;

import cl.duoc.libro_service.model.Genero;
import cl.duoc.libro_service.service.GeneroService;
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
@RequestMapping("api/v1/generos")
@Tag(name = "Géneros", description = "Operaciones CRUD para gestion de generos literarios")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @Operation(summary = "Listar todos los generos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de generos obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(generoService.findAll());
    }

    @Operation(summary = "Buscar genero por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genero encontrado"),
            @ApiResponse(responseCode = "404", description = "Genero no encontrado")
    })
    @GetMapping("/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(generoService.findById(codigo));
    }

    @Operation(summary = "Registrar nuevo genero")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Genero creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Genero genero) {
        Genero nuevo = generoService.save(genero);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar genero por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Genero actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Genero no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo, @Valid @RequestBody Genero genero) {
        return ResponseEntity.ok(generoService.update(codigo, genero));
    }

    @Operation(summary = "Eliminar genero por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genero eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Genero no encontrado")
    })
    @DeleteMapping("/{codigo}")
    public ResponseEntity<?> borrar(@PathVariable String codigo) {
        generoService.delete(codigo);
        return ResponseEntity.noContent().build();
    }
}