
package proyecto_grupo_2;
import java.math.BigDecimal;

public class HabitacionEstandar extends Habitacion {
    //No tiene nuevos atributos porque como la habitacion es estandar tendra los 
    //mismos que la clase padre por ende solo hacemos uso del super y ya 
    
    public HabitacionEstandar(int numero, BigDecimal tarifaBase){
        super(numero,"Estandar", tarifaBase);
        
    }
    
    
}
