package cl.duoc.notificacion_service.service;

import cl.duoc.notificacion_service.dto.NotificacionDTO;
import cl.duoc.notificacion_service.mapper.NotificacionMapper;
import cl.duoc.notificacion_service.model.Notificacion;
import cl.duoc.notificacion_service.repository.NotificacionRepository;
import cl.duoc.notificacion_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private NotificacionMapper notificacionMapper;

    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    public NotificacionDTO findById(Long id) {
        Notificacion notificacion = notificacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return notificacionMapper.toDTO(notificacion);
    }

    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public void delete(Long id) {
        notificacionRepository.deleteById(id);
    }

    public Notificacion update(Long id, Notificacion notificacion) {
        Notificacion notificacionActualizar = notificacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        notificacionActualizar.setUsuarioId(notificacion.getUsuarioId());
        notificacionActualizar.setTipo(notificacion.getTipo());
        notificacionActualizar.setMensaje(notificacion.getMensaje());
        notificacionActualizar.setLeida(notificacion.getLeida());
        notificacionActualizar.setFecha(notificacion.getFecha());

        return notificacionRepository.save(notificacionActualizar);
    }

    public List<Notificacion> findByUsuarioId(Long usuarioId) {
        return notificacionRepository.findByUsuarioId(usuarioId);
    }

    public List<Notificacion> findByLeida(Boolean leida) {
        return notificacionRepository.findByLeida(leida);
    }

    public List<Notificacion> findByTipo(String tipo) {
        return notificacionRepository.findByTipo(tipo);
    }
}
