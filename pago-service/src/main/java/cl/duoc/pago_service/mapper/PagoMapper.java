package cl.duoc.pago_service.mapper;

import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.model.Pago;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PagoMapper {

    public PagoDTO toDTO(Pago pago) {
        if (pago == null) return null;
        PagoDTO dto = new PagoDTO();
        dto.setId(pago.getId());
        dto.setPedidoId(pago.getPedidoId());
        dto.setMonto(pago.getMonto());
        dto.setMetodoPago(pago.getMetodoPago());
        dto.setEstado(pago.getEstado());
        dto.setFecha(pago.getFecha());
        return dto;
    }

    public List<PagoDTO> toDTOList(List<Pago> pagos) {
        return pagos.stream().map(this::toDTO).toList();
    }
}
