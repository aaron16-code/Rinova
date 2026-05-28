package cl.duoc.pedido_service.service;

import cl.duoc.pedido_service.clients.LibroFeign;
import cl.duoc.pedido_service.clients.UsuarioFeign;
import cl.duoc.pedido_service.dto.LibroDTO;
import cl.duoc.pedido_service.dto.PedidoDTO;
import cl.duoc.pedido_service.dto.UsuarioDTO;
import cl.duoc.pedido_service.mapper.PedidoMapper;
import cl.duoc.pedido_service.model.Pedido;
import cl.duoc.pedido_service.repository.PedidoRepository;
import cl.duoc.pedido_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private UsuarioFeign usuarioFeign;

    @Autowired
    private LibroFeign libroFeign;

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        PedidoDTO dto = pedidoMapper.toDTO(pedido);

        try {
            UsuarioDTO usuario = usuarioFeign.buscarPorId(pedido.getUsuarioId());
            dto.setUsuario(usuario);
        } catch (Exception e) {
            dto.setUsuario(null);
        }

        try {
            LibroDTO libro = libroFeign.buscarPorId(pedido.getLibroId());
            dto.setLibro(libro);
        } catch (Exception e) {
            dto.setLibro(null);
        }

        return dto;
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido update(Long id, Pedido pedido) {
        Pedido pedidoActualizar = pedidoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        pedidoActualizar.setUsuarioId(pedido.getUsuarioId());
        pedidoActualizar.setLibroId(pedido.getLibroId());
        pedidoActualizar.setCantidad(pedido.getCantidad());
        pedidoActualizar.setTotal(pedido.getTotal());
        pedidoActualizar.setEstado(pedido.getEstado());

        return pedidoRepository.save(pedidoActualizar);
    }

    public List<Pedido> findByUsuarioId(Long usuarioId) {
        return pedidoRepository.findByUsuarioId(usuarioId);
    }

    public List<Pedido> findByEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> findByLibroId(Long libroId) {
        return pedidoRepository.findByLibroId(libroId);
    }

    public List<Pedido> findAllOrdenados() {
        return pedidoRepository.findAllByOrderByFechaDesc();
    }
}
