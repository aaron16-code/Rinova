package cl.duoc.pago_service.dto;

import cl.duoc.pago_service.model.EstadoPago;
import cl.duoc.pago_service.model.MetodoPago;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PagoDTO {

    private Long id;

    @NotNull(message = "El id del pedido es obligatorio")
    private Long pedidoId;

    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotNull(message = "El método de pago es obligatorio")
    private MetodoPago metodoPago;

    private EstadoPago estado;
    private LocalDateTime fecha;
}