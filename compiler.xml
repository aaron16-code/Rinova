package cl.duoc.inventario_service.service;

import cl.duoc.inventario_service.dto.InventarioDTO;
import cl.duoc.inventario_service.mapper.InventarioMapper;
import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.repository.InventarioRepository;
import cl.duoc.inventario_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private InventarioMapper inventarioMapper;

    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    public InventarioDTO findById(Long id) {
        Inventario inventario = inventarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return inventarioMapper.toDTO(inventario);
    }

    public Inventario save(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    public void delete(Long id) {
        inventarioRepository.deleteById(id);
    }

    public Inventario update(Long id, Inventario inventario) {
        Inventario inventarioActualizar = inventarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        inventarioActualizar.setLibroId(inventario.getLibroId());
        inventarioActualizar.setStock(inventario.getStock());
        inventarioActualizar.setStockMinimo(inventario.getStockMinimo());

        return inventarioRepository.save(inventarioActualizar);
    }

    public InventarioDTO findByLibroId(Long libroId) {
        Inventario inventario = inventarioRepository.findByLibroId(libroId).orElse(null);
        return inventarioMapper.toDTO(inventario);
    }

    public List<Inventario> findStockBajoMinimo() {
        return inventarioRepository.findStockBajoMinimo();
    }

    public List<Inventario> findByStockMenorA(Integer cantidad) {
        return inventarioRepository.findByStockLessThan(cantidad);
    }
}