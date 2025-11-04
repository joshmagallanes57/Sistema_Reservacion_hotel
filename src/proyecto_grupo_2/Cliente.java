
package proyecto_grupo_2;

import java.util.Objects;

public class Cliente {
    /*
     * Creacion de la clase cliente constructor, getters y setters
     */
    private int idCliente;
    private String nombre;
    private String correo;
    private int telefono;
    public Cliente(int idCliente, String nombre, String correo, int telefono) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
    public int getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString(){
        return idCliente + ";" + nombre + ";" + correo + ";" + telefono;
    }
    
}
