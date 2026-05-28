package cl.duoc.pedido_service.clients;

import cl.duoc.pedido_service.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", url = "http://localhost:8085")
public interface UsuarioFeign {

    @GetMapping("/api/v1/usuarios/{id}")
    UsuarioDTO buscarPorId(@PathVariable Long id);
}
