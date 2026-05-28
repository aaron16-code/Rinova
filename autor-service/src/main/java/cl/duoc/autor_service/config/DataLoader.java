package cl.duoc.autor_service.config;

import cl.duoc.autor_service.model.Autor;
import cl.duoc.autor_service.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void run(String... args) throws Exception {
        if (autorRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS ***");

            Autor a1 = new Autor(null, "Gabriel García Márquez", "Colombiana", 1927);
            Autor a2 = new Autor(null, "Antoine de Saint-Exupéry", "Francesa", 1900);
            Autor a3 = new Autor(null, "George Orwell", "Británica", 1903);
            Autor a4 = new Autor(null, "Miguel de Cervantes", "Española", 1547);

            autorRepository.save(a1);
            autorRepository.save(a2);
            autorRepository.save(a3);
            autorRepository.save(a4);
        }
    }
}