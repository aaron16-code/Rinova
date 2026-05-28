package cl.duoc.pedido_service.repository;

import cl.duoc.pedido_service.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByUsuarioId(Long usuarioId);
    List<Pedido> findByLibroId(Long libroId);
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByEstadoOrderByFechaDesc(String estado);
    List<Pedido> findAllByOrderByFechaDesc();
}