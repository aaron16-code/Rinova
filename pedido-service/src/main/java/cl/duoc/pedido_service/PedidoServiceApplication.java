package cl.duoc.pedido_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Pedido Service API",
                description = "Microservicio para gestión de pedidos - Proyecto Rinova",
                version = "1.0.0",
                contact = @Contact(
                        name = "Equipo Rinova",
                        email = "rinova@duoc.cl"
                )
        )
)
@SpringBootApplication
@EnableFeignClients
public class PedidoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PedidoServiceApplication.class, args);
    }

}
