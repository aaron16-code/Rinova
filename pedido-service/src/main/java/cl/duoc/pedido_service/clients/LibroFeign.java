package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.LibroDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "libro-service", url = "${libro-service.url}")
public interface LibroFeign {

    @GetMapping("/api/v1/libros/{id}")
    LibroDTO buscarPorId(@PathVariable Long id);
}
