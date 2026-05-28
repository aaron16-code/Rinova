package cl.duoc.notificacion_service.mapper;

import cl.duoc.notificacion_service.dto.NotificacionDTO;
import cl.duoc.notificacion_service.model.Notificacion;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class NotificacionMapper {

    public NotificacionDTO toDTO(Notificacion notificacion) {
        if (notificacion == null) return null;
        NotificacionDTO dto = new NotificacionDTO();
        dto.setId(notificacion.getId());
        dto.setUsuarioId(notificacion.getUsuarioId());
        dto.setTipo(notificacion.getTipo());
        dto.setMensaje(notificacion.getMensaje());
        dto.setLeida(notificacion.getLeida());
        dto.setFecha(notificacion.getFecha());
        return dto;
    }

    public List<NotificacionDTO> toDTOList(List<Notificacion> notificaciones) {
        return notificaciones.stream().map(this::toDTO).toList();
    }
}
