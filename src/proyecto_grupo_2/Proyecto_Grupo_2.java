
package proyecto_grupo_2;
import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Proyecto_Grupo_2 {

    public static void main(String[] args) {
        
        
        
    boolean clienteQuiereSalir = false;
        Scanner sc = new Scanner(System.in);

        RegistroHotel hotel = new RegistroHotel("Hotel Salary");

        while (!clienteQuiereSalir) {
            System.out.println("======== MENÚ PRINCIPAL ========");
            System.out.println("1. Registrar habitación");
            System.out.println("2. Registrar cliente");
            System.out.println("3. Listar habitaciones");
            System.out.println("4. Listar clientes");
            System.out.println("5. Buscar habitación por número");
            System.out.println("6. Buscar habitaciones por tipo (Estandar/Suite)");
            System.out.println("7. Crear reserva");
            System.out.println("8. Salir");
            System.out.println("========================");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("Número de habitación:");
                    int num = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Tipo (Estandar/Suite):");
                    String tipo = sc.nextLine();
                    System.out.println("Tarifa base:");
                    BigDecimal tarifa = new BigDecimal(sc.nextLine());
                    if (tipo.equalsIgnoreCase("Suite")) {
                        System.out.println("Recargo adicional:");
                        BigDecimal recargo = new BigDecimal(sc.nextLine());
                        hotel.registrarHabitacion(new HabitacionSuite(num, tarifa, recargo));
                    } else {
                        hotel.registrarHabitacion(new HabitacionEstandar(num, tarifa));
                    }
                    break;

                case "2":
                    System.out.println("Cédula del cliente:");
                    String cedula = sc.nextLine();
                    System.out.println("Nombre del cliente:");
                    String nombre = sc.nextLine();
                    hotel.registrarCliente(new Cliente(cedula, nombre));
                    break;

                case "3":
                    hotel.mostrarHabitaciones();
                    break;

                case "4":
                    hotel.mostrarCliente();
                    break;

                case "5":
                    System.out.println("Ingrese número de habitación:");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    Habitacion h = hotel.buscarHabitacionPorNumero(numero);
                    if (h != null) System.out.println(h);
                    else System.out.println("No se encontró la habitación.");
                    break;

                case "6":
                    System.out.println("Ingrese tipo (Estandar/Suite):");
                    String t = sc.nextLine();
                    var lista = hotel.buscarHabitacionesPorTipo(t);
                    if (lista.isEmpty()) System.out.println("No hay habitaciones de ese tipo.");
                    else lista.forEach(System.out::println);
                    break;

                case "7":
                    try {
                        System.out.println("Ingrese id de reserva:");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Cédula del cliente:");
                        String c = sc.nextLine();
                        Cliente cliente = null;
                        for (Cliente cli : hotel.getClientes()) {
                            if (cli.getCedula().equals(c)) cliente = cli;
                        }
                        System.out.println("Número de habitación:");
                        int nh = sc.nextInt();
                        sc.nextLine();
                        Habitacion hab = hotel.buscarHabitacionPorNumero(nh);
                        System.out.println("Fecha entrada (YYYY-MM-DD):");
                        LocalDate e = LocalDate.parse(sc.nextLine());
                        System.out.println("Fecha salida (YYYY-MM-DD):");
                        LocalDate s = LocalDate.parse(sc.nextLine());
                        hotel.crearReserva(id, cliente, hab, e, s);
                    } catch (HabitacionNoDisponible ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                    break;

                case "8":
                    clienteQuiereSalir = true;
                    System.out.println("Gracias por su visita");
                    break;

                default:
                    System.out.println("Opción inválida, intente de nuevo...");
                    break;
            }
        }
        sc.close();
    }
    
}
