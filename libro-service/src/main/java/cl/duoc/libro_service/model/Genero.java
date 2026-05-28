package cl.duoc.libro_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @Size(max = 10, message = "El codigo no puede tener más de 10 caracteres")
    private String codigo;

    @NotBlank(message = "El titulo no puede estar vacío")
    @Size(max = 50, message = "El titulo no puede tener más de 50 caracteres")
    private String titulo;

    @NotBlank(message = "La descripcion no puede estar vacía")
    private String descripcion;
}