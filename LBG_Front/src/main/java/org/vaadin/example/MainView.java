package org.vaadin.example;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private Grid<Usuario> grid = new Grid<>(Usuario.class);

    public MainView() {
        setSizeFull();
        configureGrid();
        add(grid);
        cargarUsuarios();

        Button botonAñadir = new Button("Añadir usuario", e -> {
            // Aquí se implementará un Dialog con formulario
            Notification.show("Formulario de alta no implementado aún");
        });

        Button botonPDF = new Button("Generar PDF", e -> generarPDF());

        add(botonAñadir, botonPDF);
    }

    private void configureGrid() {
        grid.setColumns("id", "nombre", "apellidos", "email");
        grid.addComponentColumn(usuario -> new Button("Ver", e -> mostrarDialogo(usuario)))
                .setHeader("Detalles");
    }

    private void cargarUsuarios() {
        // Simulación: sustituir por llamada HTTP real
        List<Usuario> usuarios = List.of(
                new Usuario(1, "Ana", "López", "ana@example.com", new Direccion("Calle A", "Madrid", "28001"), new MetodoPago("Tarjeta", "1234")),
                new Usuario(2, "Luis", "Gómez", "luis@example.com", new Direccion("Calle B", "Sevilla", "41001"), new MetodoPago("PayPal", "luis@email.com"))
        );
        grid.setItems(usuarios);
    }

    private void mostrarDialogo(Usuario usuario) {
        Dialog dialog = new Dialog();

        Paragraph contenido = new Paragraph(
                "ID: " + usuario.getId() + "\n" +
                        "Nombre: " + usuario.getNombre() + "\n" +
                        "Apellidos: " + usuario.getApellidos() + "\n" +
                        "Email: " + usuario.getEmail() + "\n" +
                        "Dirección: " + usuario.getDireccion() + "\n" +
                        "Método de pago: " + usuario.getMetodoPago()
        );

        dialog.add(contenido);
        dialog.open();
    }

    private void generarPDF() {
        try {
            String url = "http://localhost:8080/api/pdf";
            InputStream inputStream = new URL(url).openStream();

            StreamResource resource = new StreamResource("usuarios.pdf", () -> inputStream);
            Anchor anchor = new Anchor(resource, "Descargar PDF");
            anchor.getElement().setAttribute("download", true);

            Dialog dialog = new Dialog();
            dialog.add(new Paragraph("Tu archivo está listo:"), anchor);
            dialog.open();

        } catch (IOException e) {
            e.printStackTrace();
            Notification.show("Error al generar PDF");
        }
    }
}