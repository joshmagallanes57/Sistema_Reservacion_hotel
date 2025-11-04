
package proyecto_grupo_2;
import java.math.BigDecimal;


public class HabitacionSuite extends Habitacion {
    private BigDecimal recargo;
    /*
     * Clase hija de la clase abstracta habitacion - Uso de herencia
     */
    public HabitacionSuite(int idHabitacion, int capacidadPersonas, BigDecimal precioNoche, EstadoHabitacion estadoHabitacion,TipoHabitacion tipo, BigDecimal recargo){
        super(idHabitacion,capacidadPersonas,precioNoche,estadoHabitacion,tipo);
        this.recargo = (recargo.compareTo(BigDecimal.ZERO) <= 0) ? BigDecimal.ONE : recargo;
    }
    /*Sobre escritura de metodo abstrato */
    @Override
    public BigDecimal tarifaBase(){
        return precioNoche.add(recargo);
    }

    public BigDecimal getRecargo() {
        return recargo;
    }

    public void setRecargo(BigDecimal recargo) {
        this.recargo = recargo;
    }
    /*Sobre escritura de mensaje String */
    @Override
    public String toString(){
         return idHabitacion + ";" + capacidadPersonas + ";" + precioNoche + ";" + estadoHabitacion + ";" + tipo+";"+recargo;
    }
}
