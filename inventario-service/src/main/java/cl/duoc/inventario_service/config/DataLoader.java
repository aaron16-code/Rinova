package cl.duoc.inventario_service.config;

import cl.duoc.inventario_service.model.Inventario;
import cl.duoc.inventario_service.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (inventarioRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS ***");

            Inventario i1 = new Inventario(null, 1L, 50, 10);
            Inventario i2 = new Inventario(null, 2L, 30, 5);
            Inventario i3 = new Inventario(null, 3L, 8, 10);
            Inventario i4 = new Inventario(null, 4L, 15, 5);

            inventarioRepository.save(i1);
            inventarioRepository.save(i2);
            inventarioRepository.save(i3);
            inventarioRepository.save(i4);
        }
    }
}