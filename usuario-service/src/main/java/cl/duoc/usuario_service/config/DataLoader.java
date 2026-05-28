package cl.duoc.usuario_service.config;

import cl.duoc.usuario_service.model.Usuario;
import cl.duoc.usuario_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            System.out.println("*** CARGANDO DATOS ***");

            Usuario u1 = new Usuario(null, "Pedro García", "pedro@gmail.com", "1234", "CLIENTE");
            Usuario u2 = new Usuario(null, "Juan López", "juan@gmail.com", "5678", "CLIENTE");
            Usuario u3 = new Usuario(null, "María Martínez", "maria@gmail.com", "abcd", "ADMIN");
            Usuario u4 = new Usuario(null, "Diego Silva", "diego@gmail.com", "clave", "OPERADOR");

            usuarioRepository.save(u1);
            usuarioRepository.save(u2);
            usuarioRepository.save(u3);
            usuarioRepository.save(u4);
        }
    }
}