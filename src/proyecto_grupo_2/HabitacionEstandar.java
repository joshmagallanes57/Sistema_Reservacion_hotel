
package proyecto_grupo_2;
import java.math.BigDecimal;

public class HabitacionEstandar extends Habitacion {
    /*
     * Clase hija de habitacion no se agregan atributos extras
     */
    public HabitacionEstandar(int idHabitacion, int capacidadPersonas, BigDecimal precioNoche, EstadoHabitacion estadoHabitacion,TipoHabitacion tipo){
        super(idHabitacion,capacidadPersonas,precioNoche,estadoHabitacion,tipo);
    }
    
    @Override
    public BigDecimal tarifaBase(){
        return precioNoche;
    }
}
