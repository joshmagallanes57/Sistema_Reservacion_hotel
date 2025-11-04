
package proyecto_grupo_2;
import java.math.BigDecimal;




public abstract class Habitacion {
  /*
   * Clase abstracta molde para la creacion de los diversos tipos de habitaciones
   */
   protected int idHabitacion; 
   protected int capacidadPersonas; 
   protected BigDecimal precioNoche;
   protected EstadoHabitacion estadoHabitacion;
   protected TipoHabitacion tipo;

   public Habitacion(int idHabitacion, int capacidadPersonas, BigDecimal precioNoche, EstadoHabitacion estadoHabitacion,TipoHabitacion tipo) {
     if(capacidadPersonas<=0){
       throw new IllegalArgumentException("Debe haber al menos una persona.");
      }
      if(precioNoche.compareTo(BigDecimal.ZERO)<=0){
        throw new IllegalArgumentException("La habitacionnopuede tener valores nulos o negativos");
      }
      this.idHabitacion = idHabitacion;
      this.capacidadPersonas = capacidadPersonas;
      this.precioNoche = precioNoche ;
      this.estadoHabitacion = estadoHabitacion;
      this.tipo = tipo;
   }
   /*
    * Metodos para estados de las habitaciones se heredan a las clases hijas de habitaciones
    */
   public void ponerEnMantenimiento() {
      this.estadoHabitacion = EstadoHabitacion.MANTENIMIENTO;
   }

   public void ponerDisponible() {
       this.estadoHabitacion = EstadoHabitacion.DISPONIBLE;
   }

   /*Metodo abstracto obligatorio su uso en las clases hijas para obtener una base del precio de la habitacion */
   public abstract BigDecimal tarifaBase();
   
   /* Getter and setters */
   public int getIdHabitacion() {
     return idHabitacion;
   }
   public void setIdHabitacion(int idHabitacion) {
     this.idHabitacion = idHabitacion;
   }
   public int getCapacidadPersonas() {
     return capacidadPersonas;
   }
   public void setCapacidadPersonas(int capacidadPersonas) {
     this.capacidadPersonas = capacidadPersonas;
   }
   public BigDecimal getPrecioNoche() {
     return precioNoche;
   }
   public void setPrecioNoche(BigDecimal precioNoche) {
     this.precioNoche = precioNoche;
   }
   public EstadoHabitacion getEstadoHabitacion() {
     return estadoHabitacion;
   }
   public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
     this.estadoHabitacion = estadoHabitacion;
   }
   public TipoHabitacion getTipo() {
     return tipo;
   }
   public void setTipo(TipoHabitacion tipo) {
     this.tipo = tipo;
   }
   
   @Override
   public String toString(){
     return idHabitacion + ";" + capacidadPersonas + ";" + precioNoche + ";" + estadoHabitacion + ";" + tipo;
   }
    
}
