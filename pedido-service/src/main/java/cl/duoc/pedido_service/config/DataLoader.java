package cl.duoc.pedido_service.config;

import cl.duoc.pedido_service.model.Pedido;
import cl.duoc.pedido_service.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (pedidoRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS ***");

            Pedido p1 = new Pedido(null, 1L, 1L, 2, 29980.0, LocalDate.now(), "PENDIENTE");
            Pedido p2 = new Pedido(null, 2L, 2L, 1, 8990.0, LocalDate.now(), "COMPLETADO");
            Pedido p3 = new Pedido(null, 1L, 3L, 3, 35970.0, LocalDate.now(), "PENDIENTE");
            Pedido p4 = new Pedido(null, 3L, 4L, 1, 16990.0, LocalDate.now(), "CANCELADO");

            pedidoRepository.save(p1);
            pedidoRepository.save(p2);
            pedidoRepository.save(p3);
            pedidoRepository.save(p4);
        }
    }
}