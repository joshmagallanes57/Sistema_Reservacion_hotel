
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
            throws HabitacionNoDisponible, FechaInvalidaException {

        if (!salida.isAfter(entrada)) {
            throw new FechaInvalidaException("No se permiten reservas con cero noches.");
        }
        if (habitacion.getEstado() == EstadoHabitacion.MANTENIMIENTO) {
            throw new HabitacionNoDisponible("La habitación está en mantenimiento.");
        }
        
        if (haySolapamiento(habitacion, entrada, salida)) {
            throw new HabitacionNoDisponible("La habitación ya tiene una reserva activa que se solapa.");
        }

        Reserva r = new Reserva(id, cliente, habitacion, entrada, salida);
        r.setEstado(EstadoReserva.CONFIRMADA); 
        reservas.add(r);
        System.out.println("Reserva CONFIRMADA con exito: " + r);
        }
    
    
    //para mi nueva exception de las fechas
    private boolean haySolapamiento(Habitacion habitacion, LocalDate entrada, LocalDate salida) {
    //recorrer todas las reservas registradas en el hotel
    for (Reserva r : reservas) {
        
        //verificar la misma habitación
        if (r.getHabitacion().equals(habitacion)) {
            
            //verificar reserva (CONFIRMADA o PENDIENTE)
            if (r.getEstado() == EstadoReserva.CONFIRMADA || r.getEstado() == EstadoReserva.PENDIENTE) {
                //condicion 1 o condicion 2
                
                LocalDate rEntrada = r.getFechaEntrada();
                LocalDate rSalida = r.getFechaSalida();
                
                boolean solapa = entrada.isBefore(rSalida) && rEntrada.isBefore(salida);
                
                if (solapa) {
                    return true;
                }
            }
        }
    } return false;
    }
    public Cliente buscarClientePorCedula(String cedula) {
        for (Cliente c : clientes) {
        // el metodo que comapara 
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }
    
}
