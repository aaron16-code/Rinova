package cl.duoc.inventario_service.repository;

import cl.duoc.inventario_service.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findByLibroId(Long libroId);
    List<Inventario> findByStockLessThan(Integer stock);

    @Query("SELECT i FROM Inventario i WHERE i.stock <= i.stockMinimo")
    List<Inventario> findStockBajoMinimo();
}