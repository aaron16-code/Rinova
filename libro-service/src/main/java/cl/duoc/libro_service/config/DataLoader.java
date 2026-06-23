package cl.duoc.libro_service.config;

import cl.duoc.libro_service.model.Genero;
import cl.duoc.libro_service.model.Libro;
import cl.duoc.libro_service.repository.GeneroRepository;
import cl.duoc.libro_service.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (generoRepository.count() == 0) {
            System.out.println("*** CARGANDO GÉNEROS ***");
            generoRepository.save(new Genero("NOVELA",    "Novela",    "Obras de ficción narrativa extensa"));
            generoRepository.save(new Genero("INFANTIL",  "Infantil",  "Libros dirigidos al público infantil"));
            generoRepository.save(new Genero("DISTOPIA",  "Distopía",  "Narrativa de sociedades futuras opresivas"));
            generoRepository.save(new Genero("CLASICO",   "Clásico",   "Obras literarias de reconocido valor histórico"));
        }

        if (libroRepository.count() == 0) {
            System.out.println("*** CARGANDO LIBROS ***");
            libroRepository.save(new Libro(null, "Cien años de soledad", "978-0-06-088328-7", "NOVELA",   1967, 14990.0, 1L));
            libroRepository.save(new Libro(null, "El principito",        "978-0-15-601219-5", "INFANTIL", 1943,  8990.0, 2L));
            libroRepository.save(new Libro(null, "1984",                 "978-0-45-228285-3", "DISTOPIA", 1949, 11990.0, 3L));
            libroRepository.save(new Libro(null, "Don Quijote",          "978-8-41-376608-7", "CLASICO",  1605, 16990.0, 4L));
        }
    }
}
