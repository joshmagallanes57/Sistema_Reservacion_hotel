
package proyecto_grupo_2;

import java.util.Objects;

public class Cliente {
    //Atributos
    private String cedula;
    private String nombre;
    
    //Constructor
    public Cliente(String cedula, String nombre){
        this.cedula=cedula;
        this.nombre=nombre;
    }
    
    //Getters
    
    public String getCedula(){
        return cedula;
        
    }
    
    public String getNombre(){
        return nombre;
    }
    
    //Metodos 
    @Override
    public String toString(){
        return "Cliente{" + "cedula= '" + cedula+ '\''
                + ", nombre='" + nombre+ '\''+ '}';
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return cedula.equals(cliente.cedula);
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
    
    
    
    
    
    
}
