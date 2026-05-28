package cl.duoc.libro_service.mapper;

import cl.duoc.libro_service.dto.LibroDTO;
import cl.duoc.libro_service.model.Libro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibroMapper {

    public LibroDTO toDTO(Libro libro) {
        LibroDTO dto = new LibroDTO();
        dto.setId(libro.getId());
        dto.setTitulo(libro.getTitulo());
        dto.setIsbn(libro.getIsbn());
        dto.setGenero(libro.getGenero());
        dto.setAnioPublicacion(libro.getAnioPublicacion());
        dto.setPrecio(libro.getPrecio());
        dto.setAutorId(libro.getAutorId());
        return dto;
    }

    public List<LibroDTO> toDTOList(List<Libro> libros) {
        return libros.stream().map(this::toDTO).toList();
    }
}