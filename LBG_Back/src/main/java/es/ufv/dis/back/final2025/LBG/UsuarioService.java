package es.ufv.dis.back.final2025.LBG;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private List<Usuario> usuarios;
    private final LectorJson lector;

    public UsuarioService() {
        lector = new LectorJson();
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
        guardarCambios();
    }

    public void add(Usuario usuario) {
        usuarios.add(usuario);
        guardarCambios();
    }

    public void update(String id, Usuario datos) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getId().toString().equals(id)) {
                usuarios.set(i, datos);
                guardarCambios();
                return;
            }
        }
    }

    private void guardarCambios() {
        lector.escribeFicheroJson(usuarios);
    }

    public void generarPDF() {
        GeneradorPDF generador = new GeneradorPDF();
        generador.generar(usuarios);
    }
}