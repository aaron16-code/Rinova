package cl.duoc.libro_service.repository;

import cl.duoc.libro_service.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    List<Libro> findByGeneroId(String generoId);
    List<Libro> findByAutorId(Long autorId);
    List<Libro> findByPrecioBetween(Double min, Double max);

    @Query("SELECT l FROM Libro l WHERE LOWER(l.titulo) LIKE LOWER(CONCAT('%', :titulo, '%'))")
    List<Libro> buscarPorTitulo(@Param("titulo") String titulo);
}