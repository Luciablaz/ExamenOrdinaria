package es.ufv.dis.back.final2025.LBG;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService servicio;

    public UsuarioController(UsuarioService servicio) {
        this.servicio = servicio;
    }

    /* ------------------- CONSULTAS ------------------- */

    /** Devuelve la lista completa de usuarios */
    @GetMapping
    public List<Usuario> getUsuarios() {
        return servicio.findAll();
    }

    /** Devuelve un usuario por id */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable String id) {
        Usuario usuario = servicio.findById(id);
        return (usuario != null) ? ResponseEntity.ok(usuario)
                : ResponseEntity.notFound().build();
    }

    /* ------------------- CREAR ------------------- */

    /** Crea un nuevo usuario */
    @PostMapping
    public ResponseEntity<Void> addUsuario(@RequestBody Usuario usuario) {
        servicio.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* ------------------- ACTUALIZAR ------------------- */

    /** Actualiza un usuario existente */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsuario(@PathVariable String id,
                                              @RequestBody Usuario datosActualizados) {
        Usuario existente = servicio.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.update(id, datosActualizados);
        return ResponseEntity.ok().build();
    }

    /* ------------------- ELIMINAR ------------------- */

    /** Elimina un usuario */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable String id) {
        servicio.delete(id);
        return ResponseEntity.noContent().build();
    }

    /* ------------------- GENERAR PDF (opcional) ------------------- */

    /** Lanza la generación del PDF con todos los usuarios */
    @GetMapping("/generar-pdf")
    public ResponseEntity<Void> generarPdf() {
        servicio.generarPDF();            // Si añadiste este método en el service
        return ResponseEntity.ok().build();
    }
}
