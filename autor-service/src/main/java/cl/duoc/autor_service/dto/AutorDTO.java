package cl.duoc.autor_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AutorDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La nacionalidad no puede estar vacía")
    private String nacionalidad;

    @NotNull(message = "El año de nacimiento es obligatorio")
    @Min(value = 1000, message = "Año inválido")
    @Max(value = 2100, message = "Año inválido")
    private Integer anioNacimiento;
}