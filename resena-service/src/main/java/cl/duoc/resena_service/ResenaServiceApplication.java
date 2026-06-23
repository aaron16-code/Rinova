package cl.duoc.resena_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Resena Service API",
                description = "Microservicio para gestión de reseñas - Proyecto Rinova",
                version = "1.0.0",
                contact = @Contact(
                        name = "Equipo Rinova",
                        email = "rinova@duoc.cl"
                )
        )
)
@SpringBootApplication
@EnableDiscoveryClient
public class ResenaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResenaServiceApplication.class, args);
    }

}
