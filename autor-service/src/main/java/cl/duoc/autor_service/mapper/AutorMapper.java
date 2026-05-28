package cl.duoc.autor_service.mapper;

import cl.duoc.autor_service.dto.AutorDTO;
import cl.duoc.autor_service.model.Autor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutorMapper {

    public AutorDTO toDTO(Autor autor) {
        AutorDTO dto = new AutorDTO();
        dto.setId(autor.getId());
        dto.setNombre(autor.getNombre());
        dto.setNacionalidad(autor.getNacionalidad());
        dto.setAnioNacimiento(autor.getAnioNacimiento());
        return dto;
    }

    public List<AutorDTO> toDTOList(List<Autor> autores) {
        return autores.stream().map(this::toDTO).toList();
    }
}