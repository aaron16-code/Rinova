package cl.duoc.pago_service.repository;

import cl.duoc.pago_service.model.EstadoPago;
import cl.duoc.pago_service.model.MetodoPago;
import cl.duoc.pago_service.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByPedidoId(Long pedidoId);
    List<Pago> findByEstado(EstadoPago estado);
    List<Pago> findByMetodoPago(MetodoPago metodoPago);
}