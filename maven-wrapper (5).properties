package cl.duoc.libro_service.service;

import cl.duoc.libro_service.dto.LibroDTO;
import cl.duoc.libro_service.mapper.LibroMapper;
import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.repository.LibroRepository;
import cl.duoc.libro_service.repository.GeneroRepository;
import cl.duoc.libro_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Autowired
    private LibroMapper libroMapper;

    public List<Libro> findAll() {
        return libroRepository.findAll();
    }

    public LibroDTO findById(Long id) {
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return libroMapper.toDTO(libro);
    }

    public Libro save(Libro libro) {
        if (!generoRepository.existsById(libro.getGeneroId())) {
            throw new ResourceNotFoundException("Genero con codigo " + libro.getGeneroId() + " no existe");
        }
        return libroRepository.save(libro);
    }

    public void delete(Long id) {
        libroRepository.deleteById(id);
    }

    public Libro update(Long id, Libro libro) {
        Libro libroActualizar = libroRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        if (!generoRepository.existsById(libro.getGeneroId())) {
            throw new ResourceNotFoundException("Genero con codigo " + libro.getGeneroId() + " no existe");
        }

        libroActualizar.setTitulo(libro.getTitulo());
        libroActualizar.setIsbn(libro.getIsbn());
        libroActualizar.setGeneroId(libro.getGeneroId());
        libroActualizar.setAnioPublicacion(libro.getAnioPublicacion());
        libroActualizar.setPrecio(libro.getPrecio());
        libroActualizar.setAutorId(libro.getAutorId());

        return libroRepository.save(libroActualizar);
    }

    public List<Libro> findByGeneroId(String generoId) {
        return libroRepository.findByGeneroId(generoId);
    }

    public List<Libro> findByAutorId(Long autorId) {
        return libroRepository.findByAutorId(autorId);
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.buscarPorTitulo(titulo);
    }

    public List<Libro> findByPrecioBetween(Double min, Double max) {
        return libroRepository.findByPrecioBetween(min, max);
    }
}