package org.vaadin.example;

import com.google.gson.Gson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

/**
 * Diálogo modal para dar de alta un nuevo usuario.
 * Envía los datos al backend mediante HTTP POST /api/usuarios
 */
public class UsuarioForm extends Dialog {

    /* Campos del formulario */
    private final TextField id           = new TextField("ID");
    private final TextField nombre       = new TextField("Nombre");
    private final TextField apellidos    = new TextField("Apellidos");
    private final TextField email        = new TextField("Email");
    private final TextField calle        = new TextField("Calle");
    private final TextField ciudad       = new TextField("Ciudad");
    private final TextField codigoPostal = new TextField("Código Postal");

    private final Gson gson = new Gson();

    /**
     * @param onSuccess  callback para refrescar la tabla en MainView
     */
    public UsuarioForm(Runnable onSuccess) {

        /* Distribución del formulario */
        FormLayout form = new FormLayout(
                id, nombre, apellidos, email,
                calle, ciudad, codigoPostal
        );

        /* Botón Guardar -> envía JSON al backend */
        Button guardar = new Button("Guardar", click -> {
            try {
                Direccion dir = new Direccion(
                        calle.getValue(),
                        ciudad.getValue(),
                        codigoPostal.getValue()
                );

                Usuario nuevo = new Usuario(
                        Integer.parseInt(id.getValue()),
                        nombre.getValue(),
                        apellidos.getValue(),
                        email.getValue(),
                        dir,
                        null          // método de pago (añádelo si lo necesitas)
                );

                /* POST al backend */
                String json = new Gson().toJson(nuevo);  // convierte a JSON
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/usuarios")) // <-- backend
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json))
                        .build();

                client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                        .thenAccept(response -> Notification.show("Usuario guardado"))
                        .exceptionally(ex -> {
                            Notification.show("Error al guardar: " + ex.getMessage());
                            return null;
                        });

                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());

                Notification.show("Usuario añadido");
                close();
                onSuccess.run();          // refresca el Grid de MainView
            } catch (Exception ex) {
                ex.printStackTrace();
                Notification.show("Error al guardar usuario");
            }
        });

        VerticalLayout layout = new VerticalLayout(form, guardar);
        add(layout);
        setWidth("420px");
    }
}