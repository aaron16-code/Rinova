package cl.duoc.resena_service.service;

import cl.duoc.resena_service.dto.ResenaDTO;
import cl.duoc.resena_service.mapper.ResenaMapper;
import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.repository.ResenaRepository;
import cl.duoc.resena_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private ResenaMapper resenaMapper;

    public List<Resena> findAll() {
        return resenaRepository.findAll();
    }

    public ResenaDTO findById(Long id) {
        Resena resena = resenaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return resenaMapper.toDTO(resena);
    }

    public Resena save(Resena resena) {
        return resenaRepository.save(resena);
    }

    public void delete(Long id) {
        resenaRepository.deleteById(id);
    }

    public Resena update(Long id, Resena resena) {
        Resena resenaActualizar = resenaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        resenaActualizar.setLibroId(resena.getLibroId());
        resenaActualizar.setUsuarioId(resena.getUsuarioId());
        resenaActualizar.setPuntuacion(resena.getPuntuacion());
        resenaActualizar.setComentario(resena.getComentario());
        resenaActualizar.setFecha(resena.getFecha());

        return resenaRepository.save(resenaActualizar);
    }

    public List<ResenaDTO> findByLibroId(Long libroId) {
        List<Resena> resenas = resenaRepository.findByLibroId(libroId);
        return resenas.stream().map(resenaMapper::toDTO).toList();
    }

    public List<ResenaDTO> findByUsuarioId(Long usuarioId) {
        List<Resena> resenas = resenaRepository.findByUsuarioId(usuarioId);
        return resenas.stream().map(resenaMapper::toDTO).toList();
    }

    public List<ResenaDTO> findByPuntuacion(Integer puntuacion) {
        List<Resena> resenas = resenaRepository
                .findByPuntuacionGreaterThanEqualOrderByPuntuacionDesc(puntuacion);
        return resenas.stream().map(resenaMapper::toDTO).toList();
    }
}
