package cl.duoc.pedido_service.mapper;

import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.model.Pedido;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoMapper {

    public PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setCantidad(pedido.getCantidad());
        dto.setTotal(pedido.getTotal());
        dto.setFecha(pedido.getFecha());
        dto.setEstado(pedido.getEstado());
        // user y libro se terminan enel service con el feign hehe
        return dto;
    }

    public List<PedidoDTO> toDTOList(List<Pedido> pedidos) {
        return pedidos.stream().map(this::toDTO).toList();
    }
}