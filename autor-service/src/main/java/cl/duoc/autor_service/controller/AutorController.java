package cl.duoc.autor_service.controller;

import cl.duoc.autor_service.dto.AutorDTO;
import cl.duoc.autor_service.model.Autor;
import cl.duoc.autor_service.service.AutorService;
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
@RequestMapping("api/v1/autores")
@Tag(name = "Autores", description = "Operaciones CRUD para gestion de autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @Operation(summary = "Listar todos los autores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente")
    })
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(autorService.findAll());
    }

    @Operation(summary = "Buscar autor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado"),
            @ApiResponse(responseCode = "204", description = "Autor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        AutorDTO autor = autorService.findById(id);
        if (autor == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(autor);
    }

    @Operation(summary = "Registrar nuevo autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody Autor autor) {
        Autor nuevo = autorService.save(autor);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar autor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable Long id) {
        autorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar autor por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Autor no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Autor autor) {
        Autor autorActualizado = autorService.update(id, autor);
        if (autorActualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(autorActualizado);
    }

    @Operation(summary = "Buscar autores por nacionalidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente")
    })
    @GetMapping("/nacionalidad/{nacionalidad}")
    public ResponseEntity<?> buscarPorNacionalidad(@PathVariable String nacionalidad) {
        return ResponseEntity.ok(autorService.findByNacionalidad(nacionalidad));
    }

    @Operation(summary = "Buscar autor por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autor encontrado")
    })
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(autorService.findByNombre(nombre));
    }

    @Operation(summary = "Buscar autores por año de nacimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente")
    })
    @GetMapping("/anio/{anio}")
    public ResponseEntity<?> buscarPorAnioNacimiento(@PathVariable Integer anio) {
        return ResponseEntity.ok(autorService.findByAnioNacimiento(anio));
    }

    @Operation(summary = "Buscar autores por rango de año de nacimiento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de autores obtenida correctamente")
    })
    @GetMapping("/rango-anio")
    public ResponseEntity<?> buscarPorRangoAnio(
            @RequestParam Integer desde,
            @RequestParam Integer hasta) {
        return ResponseEntity.ok(autorService.findByRangoAnio(desde, hasta));
    }
}