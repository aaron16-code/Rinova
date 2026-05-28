package cl.duoc.inventario_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class InventarioDTO {

    private Long id;

    @NotNull(message = "El libroId es obligatorio")
    private Long libroId;

    @NotNull(message = "stock es obligatorio")
    @Min(value = 0, message = "stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "stock minimo es obligatorio")
    @Min(value = 0, message = "stock minimo no puede ser negativo")
    private Integer stockMinimo;
}