package cl.duoc.usuario_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacio")
    @Email(message = "email no valido")
    private String email;

    @NotBlank(message = "El rol no puede estar vacio")
    private String rol;
}