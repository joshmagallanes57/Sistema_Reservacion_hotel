package proyecto_grupo_2;



import java.util.Scanner;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Proyecto_Grupo_2 {
    public static void main(String[] args) {

        /* Nos piden crear 5 habitaciones y 3 clientes --> Las habitaciones y clientes
        * estan creados desde los archivos .txt como bases
        * Desde el main gestionaremos los diferentes metodos que tendra nuestro sistema de hotel 
       */

        RegistroHotel hotel = new RegistroHotel();
        boolean clienteQuiereSalir = false;
        Scanner sc = new Scanner(System.in);
        hotel.cargarClientes();
        hotel.cargarHabitaciones();
        hotel.cargarReservas();


        while(!clienteQuiereSalir){
            System.out.println("======== MENÚ PRINCIPAL ========");
            System.out.println("1. Registrar habitaciones"); //--> metodo hecho
            System.out.println("2. Registrar cliente");// --> metodo hecho 
            System.out.println("3. Listar habitaciones"); // --> metodo hecho 
            System.out.println("4. Listar clientes"); // --> metodo hecho 
            System.out.println("5. Buscar habitación por número(Id.)"); // --> metodo hecho 
            System.out.println("6. Buscar habitaciones por tipo (Estandar/Suite)"); //-->metodo hecho
            System.out.println("7. Crear reserva"); //metodo hecho 
            System.out.println("8. Listar reservas de un cliente (Ordenadas por fechas)"); // --> metodo hecho
            System.out.println("9. Consultar habitaciones disponibles entre fechas"); //metodo hecho
            System.out.println("10. Reporte reservas entre fechas"); //metodo hecho
            System.out.println("11. Emitir factura de una reserva (por id)"); //metodo hecho 
            System.out.println("12. Salir"); //--> hecho
            System.out.println("========================");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine();
            
            switch (opcion) {
                case "1":
                    System.out.println("-----------Sistema de registro de habitaciones-----------");
                    hotel.registrarHabitaciones();
                    break;
                case "2":
                    System.out.println("-----------Sistema de registro de clientes-----------");
                    hotel.registrarCliente();
                    break;
                case "3":
                    System.out.println("------- Listar habitaciones registradas en el sistema -----------");
                    hotel.listarHabitaciones();
                    break;
                case "4":
                    System.out.println("------- Listar Clientes registrados en el sistema -----------");
                    hotel.listarClientes();
                    break;
                case "5":
                    System.out.print("Digite el id('Numero') habitacion a consultar: ");
                    try{
                        int idhabitacion = sc.nextInt();
                        sc.nextLine();
                        hotel.buscarHabitacionPorId(idhabitacion);
                        
                    }catch(Exception err){
                        sc.nextLine();
                        System.out.println("Datos ingresados equivocados.");
                    }
                    break;
                case "6":
                    try{
                        System.out.print("Buscar habitaciones por tipo (Estandar/Suite): ");
                        TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(sc.nextLine().toUpperCase());
                        System.out.println("--------------Lista de habitaciones de tipo "+tipoHabitacion+"--------------");
                        hotel.buscarHabitacionPorTipo(tipoHabitacion);
                    }catch(Exception err){
                        System.out.println("Datos ingresados equivocados.");
                    }
                    break;
                case "7":
                    System.out.println("-----------Sistema de registro de reservas-----------");
                    hotel.registrarReserva();
                    break;
                case "8":
                    try{
                        System.out.print("Ingrese id decliente a consultar datos: ");
                        hotel.listarReservasPorCliente();
                    }catch(Exception err){
                        sc.nextLine();
                        System.out.println("Datos ingresados invalidos.");
                    }
                    break;
                case "9":
                    hotel.HabitacionesDisponiblesEntreFechas();
                    break;
                case "10":
                    hotel.reporteReservasEntreFechas();
                    break;
                case "11":
                    hotel.emitirFacturaIDCReserva();
                    break;
                case "12":
                    clienteQuiereSalir = true;
                    System.out.println("Graciaspor su visita");
                    break;
                default: System.out.println("Opcion inválida intente de nuevo..."); 
            }
        }
        sc.close();
    }

    
}
