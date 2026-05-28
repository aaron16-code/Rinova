package cl.duoc.autor_service.service;

import cl.duoc.autor_service.dto.AutorDTO;
import cl.duoc.autor_service.mapper.AutorMapper;
import cl.duoc.autor_service.model.Autor;
import cl.duoc.autor_service.repository.AutorRepository;
import cl.duoc.autor_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private AutorMapper autorMapper;

    public List<Autor> findAll() {
        return autorRepository.findAll();
    }

    public AutorDTO findById(Long id) {
        Autor autor = autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return autorMapper.toDTO(autor);
    }

    public Autor save(Autor autor) {
        return autorRepository.save(autor);
    }

    public void delete(Long id) {
        autorRepository.deleteById(id);
    }

    public Autor update(Long id, Autor autor) {
        Autor autorActualizar = autorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        autorActualizar.setNombre(autor.getNombre());
        autorActualizar.setNacionalidad(autor.getNacionalidad());
        autorActualizar.setAnioNacimiento(autor.getAnioNacimiento());

        return autorRepository.save(autorActualizar);
    }

    public List<Autor> findByNacionalidad(String nacionalidad) {
        return autorRepository.findByNacionalidad(nacionalidad);
    }

    public List<Autor> findByNombre(String nombre) {
        return autorRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Autor> findByAnioNacimiento(Integer anio) {
        return autorRepository.findByAnioNacimiento(anio);
    }

    public List<Autor> findByRangoAnio(Integer desde, Integer hasta) {
        return autorRepository
                .findByAnioNacimientoBetweenOrderByAnioNacimientoAsc(desde, hasta);
    }
}