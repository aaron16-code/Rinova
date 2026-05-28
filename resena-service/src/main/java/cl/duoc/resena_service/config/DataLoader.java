package cl.duoc.resena_service.config;

import cl.duoc.resena_service.model.Resena;
import cl.duoc.resena_service.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ResenaRepository resenaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (resenaRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS DE PRUEBA PARA RESEÑAS ***");


            Resena r1 = new Resena();
            r1.setId(null);
            r1.setLibroId(1L);
            r1.setUsuarioId(1L);
            r1.setPuntuacion(5);
            r1.setComentario("Excelente libro, la trama me atrapó desde el primer capítulo.");
            r1.setFecha(LocalDateTime.now());


            Resena r2 = new Resena();
            r2.setId(null);
            r2.setLibroId(1L);
            r2.setUsuarioId(2L);
            r2.setPuntuacion(4);
            r2.setComentario("Muy buena lectura, aunque el final se sintió algo apresurado.");
            r2.setFecha(LocalDateTime.now());


            Resena r3 = new Resena();
            r3.setId(null);
            r3.setLibroId(2L);
            r3.setUsuarioId(3L);
            r3.setPuntuacion(2);
            r3.setComentario("No logró engancharme, el ritmo es demasiado lento.");
            r3.setFecha(LocalDateTime.now());


            resenaRepository.save(r1);
            resenaRepository.save(r2);
            resenaRepository.save(r3);

        }
    }
}