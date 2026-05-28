package cl.duoc.pago_service.service;

import cl.duoc.pago_service.dto.PagoDTO;
import cl.duoc.pago_service.mapper.PagoMapper;
import cl.duoc.pago_service.model.EstadoPago;
import cl.duoc.pago_service.model.MetodoPago;
import cl.duoc.pago_service.model.Pago;
import cl.duoc.pago_service.repository.PagoRepository;
import cl.duoc.pago_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PagoMapper pagoMapper;

    public List<Pago> findAll() {
        return pagoRepository.findAll();
    }

    public PagoDTO findById(Long id) {
        Pago pago = pagoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return pagoMapper.toDTO(pago);
    }

    public Pago save(Pago pago) {
        if (pago.getEstado() == null) {
            pago.setEstado(EstadoPago.PENDIENTE);
        }
        return pagoRepository.save(pago);
    }

    public void delete(Long id) {
        pagoRepository.deleteById(id);
    }

    public Pago update(Long id, Pago pago) {
        Pago pagoActualizar = pagoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        pagoActualizar.setPedidoId(pago.getPedidoId());
        pagoActualizar.setMonto(pago.getMonto());
        pagoActualizar.setMetodoPago(pago.getMetodoPago());
        pagoActualizar.setEstado(pago.getEstado());
        pagoActualizar.setFecha(pago.getFecha());

        return pagoRepository.save(pagoActualizar);
    }

    public List<Pago> findByPedidoId(Long pedidoId) {
        return pagoRepository.findByPedidoId(pedidoId);
    }

    public List<Pago> findByEstado(EstadoPago estado) {
        return pagoRepository.findByEstado(estado);
    }

    public List<Pago> findByMetodoPago(MetodoPago metodoPago) {
        return pagoRepository.findByMetodoPago(metodoPago);
    }
}