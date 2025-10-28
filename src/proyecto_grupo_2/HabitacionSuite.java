
package proyecto_grupo_2;
import java.math.BigDecimal;


public class HabitacionSuite extends Habitacion {
    //Esto seria el precio extra por una mejor habitacion
    private BigDecimal recargo;
    //Constructor de la clase
    
    public HabitacionSuite(int numero, BigDecimal tarifaBase, BigDecimal recargo){
        super(numero,"Suite", tarifaBase);
        this.recargo=recargo;
        
    }
    
    //Metodos
    //Sobreeescribo los metodos
    @Override
    public BigDecimal calcularTarifaBase(){
        return super.calcularTarifaBase().add(recargo);
    }
    
    @Override
    public String toString(){
        return super.toString()+ ", recargo= " + recargo;
    }
    
    
}
