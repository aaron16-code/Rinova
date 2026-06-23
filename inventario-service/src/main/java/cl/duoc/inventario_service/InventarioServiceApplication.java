package cl.duoc.inventario_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Inventario Service API",
                description = "Microservicio para gestión de inventario - Proyecto Rinova",
                version = "1.0.0",
                contact = @Contact(
                        name = "Equipo Rinova",
                        email = "rinova@duoc.cl"
                )
        )
)
@SpringBootApplication
@EnableDiscoveryClient
public class InventarioServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventarioServiceApplication.class, args);
    }

}
