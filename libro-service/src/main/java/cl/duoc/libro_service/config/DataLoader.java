package cl.duoc.libro_service.config;

import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public void run(String... args) throws Exception {
        if (libroRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS ***");

            Libro l1 = new Libro(null, "Cien años de soledad", "978-0-06-088328-7", "Novela", 1967, 14990.0, 1L);
            Libro l2 = new Libro(null, "El principito", "978-0-15-601219-5", "Infantil", 1943, 8990.0, 2L);
            Libro l3 = new Libro(null, "1984", "978-0-45-228285-3", "Distopía", 1949, 11990.0, 3L);
            Libro l4 = new Libro(null, "Don Quijote", "978-8-41-376608-7", "Clásico", 1605, 16990.0, 4L);

            libroRepository.save(l1);
            libroRepository.save(l2);
            libroRepository.save(l3);
            libroRepository.save(l4);
        }
    }
}