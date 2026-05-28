package cl.duoc.resena_service.mapper;

import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.model.Resena;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ResenaMapper {

    public ResenaDTO toDTO(Resena resena) {
        if (resena == null) return null;
        ResenaDTO dto = new ResenaDTO();
        dto.setId(resena.getId());
        dto.setLibroId(resena.getLibroId());
        dto.setUsuarioId(resena.getUsuarioId());
        dto.setPuntuacion(resena.getPuntuacion());
        dto.setComentario(resena.getComentario());
        dto.setFecha(resena.getFecha());
        return dto;
    }

    public List<ResenaDTO> toDTOList(List<Resena> resenas) {
        return resenas.stream().map(this::toDTO).toList();
    }
}
