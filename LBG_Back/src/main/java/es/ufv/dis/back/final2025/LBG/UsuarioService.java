package es.ufv.dis.back.final2025.LBG;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private List<Usuario> usuarios;

    public UsuarioService() {
        LectorJson lector = new LectorJson();
        this.usuarios = lector.leeFicheroJson();
    }

    public List<Usuario> findAll() {
        return usuarios;
    }

    public Usuario findById(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().toString().equals(id))
                .findFirst().orElse(null);
    }

    public void delete(String id) {
        usuarios.removeIf(u -> u.getId().toString().equals(id));
    }
}
