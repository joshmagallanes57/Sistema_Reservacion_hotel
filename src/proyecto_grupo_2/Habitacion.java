
package proyecto_grupo_2;

import java.math.BigDecimal;
import java.util.Objects;

public class Habitacion {
    private int numero;
    private String tipo;
    private BigDecimal tarifaBase;
    private EstadoHabitacion estado;
    //Constructor de la clase Habitacion
    public Habitacion(int numero, String tipo, BigDecimal tarifaBase){
       this.numero=numero;
       this.tipo=tipo;
       this.tarifaBase=tarifaBase;
       this.estado=EstadoHabitacion.DISPONIBLE;
    }
    //Getters y Setters
    
    public int getNumero(){
        return numero;
        
    }

    public String getTipo(){
        return tipo;
    }
    
    public BigDecimal getTarifaBase(){
        return tarifaBase;
    }
   
    public EstadoHabitacion getEstado(){
        return estado;
        
    }
    public void setEstado(EstadoHabitacion estado){
        this.estado=estado;
    }
    
    
    
    
    
    
    
    
    
    
    //Metodos
    public BigDecimal calcularTarifaBase(){
        return tarifaBase;
    }
    
    @Override
    public String toString() {
        return "Habitacion{" +
                "numero=" + numero +
                ", tipo='" + tipo + '\'' +
                ", tarifaBase=" + tarifaBase +
                ", estado=" + estado +
                '}';
    }
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Habitacion)) return false;
        Habitacion that = (Habitacion) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }
}
