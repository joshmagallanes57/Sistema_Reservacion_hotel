
package proyecto_grupo_2;
import proyecto_grupo_2.HabitacionNoDisponible;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RegistroHotel {
    private String nombreHotel;
    private List<Habitacion> habitaciones;
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    public RegistroHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
        this.habitaciones = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    // === Métodos básicos ===
    public void registrarHabitacion(Habitacion h) {
        habitaciones.add(h);
    }

    public void registrarCliente(Cliente c) {
        clientes.add(c);
    }

    public void mostrarHabitaciones() {
        if (habitaciones.isEmpty()) {
            System.out.println("No hay habitaciones registradas.");
        } else {
            habitaciones.forEach(System.out::println);
        }
    }

    public void mostrarCliente() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
        } else {
            clientes.forEach(System.out::println);
        }
    }

    // Buscar habitación por número
    public Habitacion buscarHabitacionPorNumero(int numero) {
        for (Habitacion h : habitaciones) {
            if (h.getNumero() == numero) {
                return h;
            }
        }
        return null;
    }

    // Buscar habitaciones por tipo
    public List<Habitacion> buscarHabitacionesPorTipo(String tipo) {
        List<Habitacion> resultado = new ArrayList<>();
        for (Habitacion h : habitaciones) {
            if (h.getTipo().equalsIgnoreCase(tipo)) {
                resultado.add(h);
            }
        }
        return resultado;
    }

    // Crear reserva (simplificada)
    public void crearReserva(int id, Cliente cliente, Habitacion habitacion,
                             LocalDate entrada, LocalDate salida)
            throws HabitacionNoDisponible {

        if (habitacion.getEstado() == EstadoHabitacion.MANTENIMIENTO) {
            throw new HabitacionNoDisponible("La habitación está en mantenimiento.");
        }

        Reserva r = new Reserva(id, cliente, habitacion, entrada, salida);
        habitacion.setEstado(EstadoHabitacion.OCUPADA);
        reservas.add(r);
        System.out.println("Reserva creada: " + r);
    }
    
}
