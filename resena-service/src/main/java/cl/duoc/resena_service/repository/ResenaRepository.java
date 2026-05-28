package cl.duoc.resena_service.repository;

import cl.duoc.resena_service.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByLibroId(Long libroId);
    List<Resena> findByUsuarioId(Long usuarioId);
    List<Resena> findByPuntuacionGreaterThanEqualOrderByPuntuacionDesc(Integer puntuacion);
}
