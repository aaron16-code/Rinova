package cl.duoc.pago_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Pago Service API",
                description = "Microservicio para gestión de pagos - Proyecto Rinova",
                version = "1.0.0",
                contact = @Contact(
                        name = "Equipo Rinova",
                        email = "rinova@duoc.cl"
                )
        )
)
@SpringBootApplication
@EnableDiscoveryClient
public class PagoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PagoServiceApplication.class, args);
    }

}
