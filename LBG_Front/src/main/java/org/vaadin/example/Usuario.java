package org.vaadin.example;
import java.util.List;

public class Usuario {

    private int id;
    private String nombre;
    private String email;
    private Direccion direccion;
    private List<MetodoPago> metodosPago;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String email, Direccion direccion, List<MetodoPago> metodosPago) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.metodosPago = metodosPago;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<MetodoPago> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(List<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", direccion=" + direccion +
                ", metodosPago=" + metodosPago +
                '}';
    }
}