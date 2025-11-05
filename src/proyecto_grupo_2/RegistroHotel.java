
package proyecto_grupo_2;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class RegistroHotel {
    //Atributos para guardar en Array objetos de los tipos de objetos (Cliente,Habitacion,Reserva)
    private ArrayList<Cliente> listaDeClientes;
    private ArrayList<Habitacion> listaDeHabitaciones;
    private ArrayList<Reserva> listaDeReservas;
    private Scanner sc;

    public RegistroHotel(){
        this.listaDeClientes = new ArrayList<>();
        this.listaDeHabitaciones = new ArrayList<>();
        this.listaDeReservas = new ArrayList<>();
        sc = new Scanner(System.in); // Scanner para lectura de datos desde consola
    }


    // ============================ CARGA DE ARCHIVOS ============================

    //metodos para leer archivos en funcion de mantener la persistencia de datos
    public void cargarClientes(){
        ArrayList<String> listaDeLineasCliente =  LecturaEscritura.leerArchivo("demo\\src\\main\\resources\\clientes.txt"); //Lectura del archivo y almacena las lineas en un array
        Integer idCliente;
        String nombre;
        String correo;
        int telefono;
        for(String lineaDeCliente : listaDeLineasCliente){//Recorre el array linea por linea
            String[] partesDeLinea = lineaDeCliente.split(";"); //separa por ; y guarda en array
            try{
                //Guarda los elementos separados por split en las variables correspondientes 
                idCliente = Integer.parseInt(partesDeLinea[0]);
                nombre = partesDeLinea[1];
                correo = partesDeLinea[2];
                telefono = Integer.parseInt(partesDeLinea[3]);
                listaDeClientes.add(new Cliente(idCliente, nombre, correo, telefono));
            }
            catch(Exception err){
                System.out.println("Hubo un error en la lectura de" + lineaDeCliente);
            }
        }
    }

    /*
     * Los siguientes dos metodos de carga de datos siguen la misma estructura 
     */
    
    public void cargarHabitaciones(){
        ArrayList<String> listaDeLineasHabitaciones =  LecturaEscritura.leerArchivo("demo\\src\\main\\resources\\habitaciones.txt");
        Integer idHabitaciones;
        Integer capacidadPeronas;
        BigDecimal precioNoche;
        EstadoHabitacion estadoHabitacion;
        TipoHabitacion tipoHabitacion;
        BigDecimal recargo;

        for(String lineaDeHabitacion : listaDeLineasHabitaciones){
            String[] partesDeLinea = lineaDeHabitacion.split(";");
            try{
                idHabitaciones = Integer.parseInt(partesDeLinea[0]);
                capacidadPeronas = Integer.parseInt(partesDeLinea[1]);
                precioNoche = new BigDecimal(partesDeLinea[2]);
                estadoHabitacion = EstadoHabitacion.valueOf(partesDeLinea[3]) ;
                tipoHabitacion = TipoHabitacion.valueOf(partesDeLinea[4]);
                if(tipoHabitacion == TipoHabitacion.ESTANDAR){
                    listaDeHabitaciones.add(new HabitacionEstandar(idHabitaciones, capacidadPeronas, precioNoche, estadoHabitacion,tipoHabitacion));
                }
                else if(tipoHabitacion == TipoHabitacion.SUITE){
                    recargo = new BigDecimal(partesDeLinea[5]);
                    listaDeHabitaciones.add(new HabitacionSuite(idHabitaciones, capacidadPeronas, precioNoche, estadoHabitacion, tipoHabitacion,recargo));
                }
            }
            catch(Exception err){
                System.out.println("Hubo un error en la lectura de" + lineaDeHabitacion);
            }
        }
    }

    public void cargarReservas(){
        ArrayList<String> listaDeLineasReservas =  LecturaEscritura.leerArchivo("demo\\src\\main\\resources\\reservas.txt");
        Integer idReserva; 
        LocalDate fechaInicio; 
        LocalDate fechaFin; 
        Integer cantidadDePersonasReserva; // Verificar que las habitaciones no sean excedidad
        EstadoReserva estadoReserva;
        Integer idCliente; 
        Integer idHabitacion;

        for(String lineaDeReserva : listaDeLineasReservas){
            String[] partesDeLinea = lineaDeReserva.split(";");
            try{
                idReserva = Integer.parseInt(partesDeLinea[0]);
                fechaInicio = LocalDate.parse(partesDeLinea[1]);
                fechaFin = LocalDate.parse(partesDeLinea[2]);
                cantidadDePersonasReserva = Integer.parseInt(partesDeLinea[3]) ;
                estadoReserva = EstadoReserva.valueOf(partesDeLinea[4]);
                idCliente = Integer.parseInt(partesDeLinea[5]);
                idHabitacion = Integer.parseInt(partesDeLinea[6]);
                listaDeReservas.add(new Reserva(idReserva, fechaInicio, fechaFin, cantidadDePersonasReserva, estadoReserva, idCliente, idHabitacion));
            }
            catch(Exception err){
                System.out.println("Hubo un error en la lectura de" + lineaDeReserva);
            }
        }
    }


    // ============================ HABITACIONES ============================

    public void registrarHabitaciones(){
        
        ArrayList<String> listaLineasHabitaciones = LecturaEscritura.leerArchivo("demo\\\\src\\\\main\\\\resources\\\\habitaciones.txt");
        int ultimoIdHabitaciones = 0;
        if (!listaLineasHabitaciones.isEmpty()) {
            //obtenemos el ultimo registro de las habitaciones escritas en el txt
            String ultimaLineaHabitaciones = listaLineasHabitaciones.get(listaLineasHabitaciones.size() - 1);
            ultimoIdHabitaciones = Integer.parseInt(ultimaLineaHabitaciones.split(";")[0]);
        }
            
        int capacidadPersonas;
        BigDecimal precioNoche;
        EstadoHabitacion estadoHabitacion;
        TipoHabitacion tipoHabitacion;
        BigDecimal recargo;
        try{
            System.out.print("Ingrese la capacidad de personas que tiene esta habitacion: ");
            capacidadPersonas = sc.nextInt();
            sc.nextLine(); //por que se suele quedar un \n y este 'lo borra'


            System.out.print("Ingrese el precio que cuesta hospedarse: ");
            precioNoche = sc.nextBigDecimal();
            sc.nextLine();

            System.out.print("Ingrese el estado de la habitacion (Disponible/Ocupada/Mantenimiento): ");
            estadoHabitacion = EstadoHabitacion.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Ingrese el tipo de la habitacion: (Suite/Estandar) ");
            tipoHabitacion = TipoHabitacion.valueOf(sc.nextLine().toUpperCase());
            Habitacion habitacion = null;
            if(tipoHabitacion == TipoHabitacion.ESTANDAR){
                ultimoIdHabitaciones++;
                habitacion = new HabitacionEstandar(ultimoIdHabitaciones, capacidadPersonas, precioNoche, estadoHabitacion,tipoHabitacion);
            }
            else if(tipoHabitacion == TipoHabitacion.SUITE){
                ultimoIdHabitaciones++;
                System.out.print("Recargo de la Habitacion Suite: ");
                recargo = sc.nextBigDecimal();
                sc.nextLine();
                habitacion = new HabitacionSuite(ultimoIdHabitaciones, capacidadPersonas, precioNoche, estadoHabitacion, tipoHabitacion,recargo);
            }
            if(habitacion != null){
                System.out.println("Habitacion registrada con exito.");
                listaDeHabitaciones.add(habitacion);
                ArrayList<String> lineaEscribir = new ArrayList<>();
                lineaEscribir.add(habitacion.toString());
                LecturaEscritura.escribirArchivo("demo\\\\src\\\\main\\\\resources\\\\habitaciones.txt", lineaEscribir);
            }
        }
        catch(Exception err){
            sc.nextLine();
            System.out.println("Hubo un error con la lectura de los datos. Entrada de datos erronea "+err.getMessage());
        }
    }


    /*
     * Guarda las lineas de la ruta de archivo.txt en un Array list 
     * Itera por linea y presenta la informacion (Archivo de habitaciones)
     */

    public void listarHabitaciones(){
        ArrayList<String> lineasHabitaciones = LecturaEscritura.leerArchivo("demo\\\\\\\\src\\\\\\\\main\\\\\\\\resources\\\\\\\\habitaciones.txt");
        for(String lineaHabitacion : lineasHabitaciones){
            System.out.println(lineaHabitacion);
        }
    }


    /*
     * Recibe un int como parametro verifica que la lista de habitaciones no este vacia 
     * luego itera por los elementos de la lista de habitaciones y compara el id de las habitaciones 
     * si un id es el mismo al ingresado devuelve el objeto de habitacion caso contrario devuelve un null
     */
    public Habitacion buscarHabitacionPorId(int idHabitacion){
        boolean idEncontrado = false;
        if(listaDeHabitaciones.isEmpty()){
            System.out.println("No existen registros de habitaciones aun.");
        }else{
            for(Habitacion habitacion : listaDeHabitaciones){
                if(habitacion.getIdHabitacion() == idHabitacion){
                    idEncontrado = true;
                    System.out.println("Informacion de la habitacion: \n" + habitacion);
                    return habitacion;
                }
            }
        }
        if(!idEncontrado){
            System.out.println("No hay una habitacion con tal id.");
        }
        return null;
    }


    // Mostrar todas las habitaciones que coincidan con el tipo - enum de tipo habitacion
      
    public void buscarHabitacionPorTipo(TipoHabitacion tipoHabitacion){
        if(listaDeHabitaciones.isEmpty()){
            System.out.println("No existen registros de habitaciones aun.");
        }
        else{
            for(Habitacion habitacion : listaDeHabitaciones){
                if(habitacion.getTipo() == tipoHabitacion){
                    System.out.println(habitacion);
                }
            }
        }
    }


    // Consultar habitaciones disponibles en un rango de fechas

    public void HabitacionesDisponiblesEntreFechas(){
        LocalDate fechaInicioFiltro;
        LocalDate fechaFinFiltro;
        boolean disponibles= false;

        try{
            System.out.print("Ingrese desde que fecha realizara las consultas: ");
            fechaInicioFiltro = validarFechas(sc.nextLine()); //uso de metodo validar fechas si no se valida arroja la exception personalizada
            System.out.print("Ingrese hasta que fecha realizara las consultas: ");
            fechaFinFiltro = validarFechas(sc.nextLine());
            if (fechaFinFiltro.isAfter(fechaInicioFiltro)){
                for(Habitacion h : listaDeHabitaciones){            
                    if(solapamientoHabitaciones(h.getIdHabitacion(),fechaInicioFiltro,fechaFinFiltro)){
                        System.out.println("- Habitación " + h.getIdHabitacion() + " (" + h.getTipo() + ")");
                        disponibles = true;
                    }
                }
                if (!disponibles) {
                    throw new HabitacionNoDisponible("No hay habitaciones disponibles en ese rango de fechas.");
                }
            }else{System.out.println("Rangosde fechas invalidos.");}
        }catch(FechaInvalida err){
            System.out.println(err.getMessage());
        }catch(HabitacionNoDisponible err){
            System.out.println(err.getMessage());
        }
        catch(Exception err){
            System.out.println("Error de datos.");
        }

    }
    


    // ============================ CLIENTES ============================

    public void registrarCliente() {
        ArrayList<String> listaLineasClientes = LecturaEscritura.leerArchivo("demo\\\\src\\\\main\\\\resources\\\\clientes.txt");

        // obtenemos el último registro del archivo de clientes
        int ultimoIdCliente=0;
        if (!listaLineasClientes.isEmpty()) {
            String ultimaLineaCliente = listaLineasClientes.get(listaLineasClientes.size() - 1);
            ultimoIdCliente = Integer.parseInt(ultimaLineaCliente.split(";")[0]);
        }
        String nombre;
        String correo;
        int telefono;

        try {
            System.out.print("Ingrese el nombre del cliente: ");
            nombre = sc.nextLine();

            System.out.print("Ingrese el correo del cliente: ");
            correo = sc.nextLine();

            System.out.print("Ingrese el teléfono del cliente: ");
            telefono = sc.nextInt();
            ultimoIdCliente++;
            // Crear cliente y agregar a la lista
            Cliente cliente = new Cliente(ultimoIdCliente, nombre, correo, telefono);
            listaDeClientes.add(cliente);
            // Guardar cliente en archivo
            ArrayList<String> lineaEscribir = new ArrayList<>();
            lineaEscribir.add(cliente.toString());
            LecturaEscritura.escribirArchivo("demo\\\\src\\\\main\\\\resources\\\\clientes.txt", lineaEscribir);
            System.out.println("Cliente registrado correctamente.");
        } 
        catch (Exception err) {
            System.out.println("Hubo un error con la lectura de los datos del cliente.");
        }
    }

     // Listar todos los clientes
    public void listarClientes(){
        ArrayList<String> lineasClientes = LecturaEscritura.leerArchivo("demo\\\\\\\\src\\\\\\\\main\\\\\\\\resources\\\\\\\\clientes.txt");
        for(String lineaCliente : lineasClientes){
            System.out.println(lineaCliente);
        }
    }

    /*Recibe un id y verifica si existe y devuele un objeto cliente */
    public Cliente buscarClienteId(int idCliente){
        for(Cliente c: listaDeClientes){
            if (c.getIdCliente() == idCliente) {
                return c;   
            }
        }
        return null;
    }

    // ============================ RESERVAS ============================

    public void registrarReserva(){
        ArrayList<String> listaLineasReservas = LecturaEscritura.leerArchivo("demo\\\\src\\\\main\\\\resources\\\\reservas.txt");
        // obtenemos el último registro de las reservas escritas en el txt
        int ultimoIdReserva = 0;
        if (!listaLineasReservas.isEmpty()) {
            String ultimaLineaReserva = listaLineasReservas.get(listaLineasReservas.size() - 1);
            ultimoIdReserva = Integer.parseInt(ultimaLineaReserva.split(";")[0]);
        }

        LocalDate fechaInicio;
        LocalDate fechaFin;
        int cantidadPersonas;
        EstadoReserva estadoReserva;
        int idCliente;
        int idHabitacion;
        Habitacion habitacion;


        try {
            //Pedir datos
            System.out.print("Ingrese la fecha de inicio (yyyy-MM-dd): ");
            fechaInicio = validarFechas(sc.nextLine());
            System.out.print("Ingrese la fecha de fin (yyyy-MM-dd): ");
            fechaFin = validarFechas(sc.nextLine());

            System.out.print("Ingrese la cantidad de personas para la reserva: ");
            cantidadPersonas = sc.nextInt();
            sc.nextLine(); // limpiar salto de línea
            
            System.out.println("Ingrese el estado de la reserva (PENDIENTE/CONFIRMADA/FINALIZADA/CANCELADA): ");
            estadoReserva = EstadoReserva.valueOf(sc.nextLine().toUpperCase());

            System.out.print("Ingrese el ID del cliente: ");
            idCliente = sc.nextInt();
            sc.nextLine();  

            System.out.print("Ingrese el ID de la habitación: ");
            idHabitacion = sc.nextInt();
            sc.nextLine();
            habitacion = buscarHabitacionPorId(idHabitacion);
            if(solapamientoHabitaciones(idHabitacion, fechaInicio, fechaFin)){
                if(habitacion!=null && habitacion.getCapacidadPersonas()>=cantidadPersonas && habitacion.getEstadoHabitacion()==EstadoHabitacion.DISPONIBLE){
                    if(fechaFin.isAfter(fechaInicio)){
                        ultimoIdReserva++;
                        Reserva reserva = new Reserva(ultimoIdReserva, fechaInicio, fechaFin, cantidadPersonas, estadoReserva, idCliente, idHabitacion);
                        habitacion.setEstadoHabitacion(EstadoHabitacion.OCUPADA);
                        listaDeReservas.add(reserva);
                        // escribir la reserva en el archivo
                        ArrayList<String> lineaEscribir = new ArrayList<>();
                        lineaEscribir.add(reserva.toString());
                        LecturaEscritura.escribirArchivo("demo\\\\src\\\\main\\\\resources\\\\reservas.txt", lineaEscribir);
            
                        System.out.println("Reserva registrada correctamente.");
    
                    } else{System.out.println("Ocurrio un error rangos invalidos");}
                }else{System.out.println("Reserva rechazada a ocurrido un error.");}
            }else{
                throw new HabitacionNoDisponible("Reserva invalida existe solapamiento entre habitaciones.");
            }

        }
        catch (FechaInvalida err) {
            System.out.println(err.getMessage());
        }
        catch (HabitacionNoDisponible err) {
           System.out.println(err.getMessage());
        }
        catch (Exception err) {
            System.out.println(" Error al registrar la reserva.");
        }
    }


    /*
     * Verifica la existencia del cliente luego comienza a buscar por reservas 
     * e imprime las del cliente ingresado
     */
    public void listarReservasPorCliente(){
        int idCliente = sc.nextInt();
        sc.nextLine();
        Cliente clienteBuscar = buscarClienteId(idCliente);
        List<Reserva> reservasOrdenadas = new ArrayList<>(listaDeReservas);
        reservasOrdenadas.sort(Comparator.comparing(Reserva::getFechaInicio).reversed());
        boolean tieneReserva = false;
        
        if(clienteBuscar!=null){
            for(Reserva reserva : listaDeReservas){
                if(reserva.getIdCliente() == idCliente){
                    tieneReserva = true;
                    System.out.println(reserva);
                }
            }
        }
        else{
            System.out.println("El id que ingreso no esta registrado.");
        }
        if(!tieneReserva){
            System.out.println("El cliente no tiene reserva");
        }
    }


    /*Reporte de las reservas entre dos fechas */
    public void reporteReservasEntreFechas(){
        LocalDate fechaInicioFiltro;
        LocalDate fechaFinFiltro;

        try{
            System.out.print("Ingrese desde que fecha realizara las consultas: ");
            fechaInicioFiltro = validarFechas(sc.nextLine());
            System.out.print("Ingrese hasta que fecha realizara las consultas: ");
            fechaFinFiltro = validarFechas(sc.nextLine());
            if (fechaFinFiltro.isAfter(fechaInicioFiltro)){
                for(Reserva r: listaDeReservas){
                    if((r.getFechaInicio().isAfter(fechaInicioFiltro) || r.getFechaInicio().isEqual(fechaInicioFiltro)) && (r.getFechaFin().isBefore(fechaFinFiltro)||r.getFechaFin().isEqual(fechaFinFiltro))){
                        System.out.println(r);
                    }
                }
            }
            else if(fechaFinFiltro.isEqual(fechaInicioFiltro)){
                System.out.println("No pueden existir reservas de una sola noche");
            }
        }catch(FechaInvalida err){
            System.out.println("Error en entrada de datos recuerde formato yyyy-MM-dd");
        }

    }

    /*
     * A traves de la id de una reserva la busca y presenta su informacion 
     */
    public Reserva buscarReservaId(){
        System.out.println("Ingrese el Id de la reserva a consultar: ");
        int idReserva = sc.nextInt();
        sc.nextLine();
        for(Reserva r: listaDeReservas){
            if (r.getIdReserva() == idReserva) {
                return r;   
            }
        }
        return null;
    }

    // ============================ VALIDACIONES ============================

    //Verfica que no exista solapamiento enre fechas 
    public boolean solapamientoHabitaciones(int idHabitacion, LocalDate inicio, LocalDate fin) throws HabitacionNoDisponible {
    if(listaDeReservas.isEmpty()){
        throw new HabitacionNoDisponible("No existen registros aun.");
    }else{
        for (Reserva r : listaDeReservas) {
            if (r.getIdHabitacion() == idHabitacion && !(fin.isBefore(r.getFechaInicio()) || inicio.isAfter(r.getFechaFin()))) {
                return false; // se solapan las fechas
            }
        }
    }
        return true;
    }

    //Validar fechas - Uso de exception personalizada 
    public LocalDate validarFechas(String fechaIngreso) throws FechaInvalida {
        try {
            LocalDate fechaValidada = LocalDate.parse(fechaIngreso);
            return fechaValidada;
        } catch (DateTimeParseException e) {
            throw new FechaInvalida("Formato de fecha inválido. Use el formato yyyy-MM-dd");
        }
    }

    //FACTURA 
    
    public void emitirFacturaIDCReserva(){
        try{
            Reserva reservaId = buscarReservaId();
            Cliente cliente = buscarClienteId(reservaId.getIdCliente());
            BigDecimal diasDeInstancia;
            Habitacion habitacionFactura;
            BigDecimal subTotal;
            BigDecimal iva = new BigDecimal("0.18");
            BigDecimal total;
            if(reservaId!=null){
                habitacionFactura = buscarHabitacionPorId(reservaId.getIdHabitacion());
                diasDeInstancia = BigDecimal.valueOf(ChronoUnit.DAYS.between(reservaId.getFechaInicio(), reservaId.getFechaFin()));
                subTotal = diasDeInstancia.multiply(habitacionFactura.tarifaBase());
                total = subTotal.add(subTotal.multiply(iva));
                System.out.println("---------Factura procesada-----------\n"+
                                    "Datos del cliente\n"+cliente+
                                    "Datos de la reserva\n"+
                                    "Dias de instancia: "+diasDeInstancia+"\n"+
                                    "Precio por noche de la habitacion"+habitacionFactura.getPrecioNoche()+"\n"+
                                    "Impuesto de iva del "+iva+"\n"+
                                    "Subtotal: "+subTotal+"\n"+
                                    "Total a pagar: "+ total);
                
            }
        }catch(Exception err){
            System.out.println("Cliente/Reserva no encontrado, emision de factura cancelada.");
        }
    }

}
