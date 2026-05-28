package cl.duoc.pedido_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private Long libroId;
    private Integer cantidad;
    private Double total;
    private LocalDate fecha;
    private String estado;

    @PrePersist
    public void asignarFecha() {
        if (this.fecha == null) {
            this.fecha = LocalDate.now();
        }
        if (this.estado == null) {
            this.estado = "PENDIENTE";
        }
    }
}