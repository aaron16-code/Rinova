package cl.duoc.usuario_service.service;

import cl.duoc.usuario_service.dto.UsuarioDTO;
import cl.duoc.usuario_service.mapper.UsuarioMapper;
import cl.duoc.usuario_service.model.Usuario;
import cl.duoc.usuario_service.repository.UsuarioRepository;
import cl.duoc.usuario_service.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));
        return usuarioMapper.toDTO(usuario);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario update(Long id, Usuario usuario) {
        Usuario usuarioActualizar = usuarioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro con id " + id + " no encontrado"));

        usuarioActualizar.setNombre(usuario.getNombre());
        usuarioActualizar.setEmail(usuario.getEmail());
        usuarioActualizar.setPassword(usuario.getPassword());
        usuarioActualizar.setRol(usuario.getRol());

        return usuarioRepository.save(usuarioActualizar);
    }

    public List<Usuario> findByRol(String rol) {
        return usuarioRepository.findByRolOrderByNombreAsc(rol);
    }

    public List<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public UsuarioDTO findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
        if (usuario == null) return null;
        return usuarioMapper.toDTO(usuario);
    }
}