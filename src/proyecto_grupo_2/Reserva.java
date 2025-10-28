
package proyecto_grupo_2;
import java.time.LocalDate;
import java.util.Objects;

public class Reserva {
    private int id;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private EstadoReserva estado;
    
    //Constructor de la clase
    public Reserva(int id, Cliente cliente, Habitacion habitacion,
                   LocalDate fechaEntrada, LocalDate fechaSalida) {
        this.id = id;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = EstadoReserva.PENDIENTE;
    }
    //Getters
    public int getId() { 
        return id; 
    }
    public Cliente getCliente() {
        return cliente; }
    public Habitacion getHabitacion() {
        return habitacion; 
    }
    public LocalDate getFechaEntrada() {
        return fechaEntrada; 
    }
    public LocalDate getFechaSalida() {
        return fechaSalida; 
    }
    public EstadoReserva getEstado() {
        return estado; 
    }
    
    //Setters
    public void setEstado(EstadoReserva estado) {
        this.estado = estado; 
    }
    
    //Metodos
    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", cliente=" + cliente.getNombre() +
                ", habitacion=" + habitacion.getNumero() +
                ", entrada=" + fechaEntrada +
                ", salida=" + fechaSalida +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return id == reserva.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}



