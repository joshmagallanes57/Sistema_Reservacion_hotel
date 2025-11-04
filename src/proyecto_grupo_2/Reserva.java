
package proyecto_grupo_2;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.math.BigDecimal;
import java.util.Objects;

/*Creacion de la clase, constructor getters y setters */

public class Reserva {
   private int idReserva; 
   private LocalDate fechaInicio; 
   private LocalDate fechaFin; 
   private int cantidadDePersonasReserva; // Verificar que las habitaciones no sean excedidad
   private EstadoReserva estadoReserva;
   private int idCliente; 
   private int idHabitacion;

   public Reserva(int idReserva, LocalDate fechaInicio, LocalDate fechaFin, int cantidadDePersonasReserva,EstadoReserva estadoReserva, int idCliente, int idHabitacion) {
      this.idReserva = idReserva;
      this.fechaInicio = fechaInicio;
      this.fechaFin = fechaFin;
      this.cantidadDePersonasReserva = cantidadDePersonasReserva;
      this.estadoReserva = estadoReserva;
      this.idCliente = idCliente;
      this.idHabitacion = idHabitacion;
   }
   
   public int getIdReserva() {
      return idReserva;
   }
   public void setIdReserva(int idReserva) {
      this.idReserva = idReserva;
   }
   public LocalDate getFechaInicio() {
      return fechaInicio;
   }
   public void setFechaInicio(LocalDate fechaInicio) {
      this.fechaInicio = fechaInicio;
   }
   public LocalDate getFechaFin() {
      return fechaFin;
   }
   public void setFechaFin(LocalDate fechaFin) {
      this.fechaFin = fechaFin;
   }
   public int getCantidadDePersonasReserva() {
      return cantidadDePersonasReserva;
   }
   public void setCantidadDePersonasReserva(int cantidadDePersonasReserva) {
      this.cantidadDePersonasReserva = cantidadDePersonasReserva;
   }
   public EstadoReserva getEstadoReserva() {
      return estadoReserva;
   }
   public void setEstadoReserva(EstadoReserva estadoReserva) {
      this.estadoReserva = estadoReserva;
   }
   public int getIdCliente() {
      return idCliente;
   }
   public void setIdCliente(int idCliente) {
      this.idCliente = idCliente;
   }
   public int getIdHabitacion() {
      return idHabitacion;
   }
   public void setIdHabitacion(int idHabitacion) {
      this.idHabitacion = idHabitacion;
   }

   
   @Override
   public String toString(){
      return idReserva + ";" + fechaInicio + ";" + fechaFin + ";" + cantidadDePersonasReserva + ";" + estadoReserva + ";" + idCliente + ";" + idHabitacion;
   }
}
