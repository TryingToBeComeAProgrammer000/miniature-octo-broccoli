package task_3_ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author uyv31
 */
public class Task_3_DS {

    public static void main(String[] args) {
        Menu();
    }

    public static void Menu() {
    Scanner scan = new Scanner(System.in);
    int opcion;

    do {
        System.out.println("\n=*=*= Menu =*=*=");
        System.out.println("1. Seguimiento de pedidos");
        System.out.println("2. Gestion de pacientes");
        System.out.println("3. Zona urbana");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opcion: ");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                pedidos();
                break;
            case 2:
                gestionPacientes();
                break;
            case 3:
                menuZonaUrbana(scan);
                break;
            case 4:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida.");
        }
    } while (opcion != 4);
}

    //Control de pedidos
    
    public static void pedidos() {

        List<String> nombre = new ArrayList<>();
        List<Double> monto = new ArrayList<>();
        List<String> metodoPago = new ArrayList<>();

        Scanner scan = new Scanner(System.in);

        for (int clientes = 0; clientes < 5; clientes++) {
            System.out.println("Ingrese el nombre del cliente");
            nombre.add(scan.nextLine());

            double valor;
            do {
                System.out.println("Ingrese el monto total del producto");
                valor = scan.nextDouble();

            } while (0 >= valor);
            monto.add(valor);
            
            scan.nextLine();


            boolean pago = false;
            while (!pago) {
                System.out.println("Ingrese el metodo de pago (1- Efectivo, 2- Tarjeta)");
                String metodoPag = scan.nextLine();

                if (metodoPag.equals("1")) {
                    metodoPago.add("Efectivo");
                    pago = true;
                } else if (metodoPag.equals("2")) {
                    metodoPago.add("Tarjeta");
                    pago = true;
                } else {
                    System.out.println("Ingrese una opcion de pago valida");
                }
            }
        }
        estadisticas(nombre, monto, metodoPago);
        reporte(nombre, monto, metodoPago);
        
    }
    
    public static void estadisticas(List<String> nombre, List<Double> monto, List<String> pagos){
    
        double montoGeneral = 0;
        int tarjeta =0, efectivo =0;
        int idMontoMax = 0, idMontoMin=0;
        double max = monto.get(0), min = monto.get(0);
        
        
        for (int i = 0; i < monto.size(); i++) {
            
            double montoActual = monto.get(i);
            montoGeneral += montoActual;
            
            if(montoActual > max){ max = montoActual; idMontoMax = i;}
            if(montoActual < min){ min = montoActual; idMontoMin = i;}
            
            if (pagos.get(i).equalsIgnoreCase("tarjeta")) {tarjeta++;}
            else if (pagos.get(i).equalsIgnoreCase("efectivo")) {efectivo++;}
        }
        
        double montoPromedio = montoGeneral / monto.size();
        System.out.println("");
        System.out.println("Estadisticas");
        System.out.println("");
        System.out.println("Total: " + montoGeneral);
        System.out.println("Promedio: " + montoPromedio);
        System.out.println("Tarjeta: " + tarjeta + "\nEfectivo: " + efectivo);
        System.out.println("Mas caro: " + nombre.get(idMontoMax) + " == " + max);
        System.out.println("Mas barato: " + nombre.get(idMontoMin) + " == " + min);

    }
    
    public static void reporte(List<String> nombre, List<Double> monto,List<String> metodoPago){
        System.out.println("");
        System.out.println("**** Reporte por cliente ****");
        
        for (int clientes = 0; clientes < nombre.size(); clientes++) {
            System.out.println("");
            System.out.println("");
            System.out.println("Su nombre es: "+nombre.get(clientes));
            System.out.println("El monto a pagar es de: "+monto.get(clientes));
            System.out.println("Usted eligio pagar con: "+metodoPago.get(clientes));
            
        }
    
    }
    
    
    //Gestion de datos de pacientes
    
    public static void gestionPacientes(){
        
        Scanner scan = new Scanner(System.in);
        HashMap <Integer, HashMap<String, String>> pacientes = new HashMap<>();
        
        for (int paciente = 0; paciente < 5; paciente++) {
            
            HashMap<String,String> datosPacientes = new HashMap<>();
            System.out.println("");
            System.out.println("Ingrese el nombre del paciente");
            String nombre = scan.nextLine();
            datosPacientes.put("Nombre", nombre);
            
            System.out.println("Ingrese la edad del paciente");
            String edad = scan.nextLine();
            datosPacientes.put("Edad", edad);
            
            System.out.println("Ingrese el diagnostico del paciente");
            String diagnostico = scan.nextLine();
            datosPacientes.put("Diagnostico", diagnostico);
            
            System.out.println("Ingrese el estado del paciente (1- Estable o 2- Critico)");
            String estado = scan.nextLine();
            datosPacientes.put("Estado", estado);
            
            pacientes.put(paciente, datosPacientes);
        }
        estadisticasPacientes(pacientes);
        reporte(pacientes);
    }
    
    public static void estadisticasPacientes(HashMap <Integer, HashMap<String, String>> pacientes){ // Recibe el mapa principal "pacientes"
        
        
        int sumaEdades =0;
        int edadesPacientes = pacientes.size();
            
        for (int p : pacientes.keySet()) {
            int edadActual = Integer.parseInt(pacientes.get(p).get("Edad"));
            sumaEdades += edadActual;
        }

        int promedioEdades = sumaEdades / edadesPacientes;
        
        int estables = 0;
        int criticos = 0;
        
        
        for (int id : pacientes.keySet()) {
            HashMap <String, String> datos = pacientes.get(id); 
            String estado = datos.get("Estado");

            
            if (estado.equals("2")) {
                criticos ++;
            }else if(estado.equals("1")){
                estables ++;
            }
        }
        
        int menorEdad = Integer.MAX_VALUE;
        int mayorEdad = Integer.MIN_VALUE;

        for (int edades : pacientes.keySet()) {
            
            HashMap <String, String> datos = pacientes.get(edades); 
            
            String edad = datos.get("Edad");
            int edadActual = Integer.parseInt(edad);
            
            if (edadActual < menorEdad){
                menorEdad = edadActual;} 
            if(edadActual > mayorEdad){
                mayorEdad = edadActual;}
        }
    }
    
    public static void reporte(HashMap <Integer, HashMap<String, String>> pacientes){
    
        
        for (int id : pacientes.keySet()) {
            HashMap <String, String> datos = pacientes.get(id);
            System.out.println("");
            System.out.println("");
            System.out.println("Su nombre es: "+datos.get("Nombre"));
            System.out.println("Su edad es: "+datos.get("Edad"));
            System.out.println("Usted tiene: "+datos.get("Diagnostico"));
            System.out.println("Su estado actual es: "+datos.get("Estado"));
        }
    
    }
    
    //Zona Urbana
    
    public static void menuZonaUrbana(Scanner scan) {
    inicializarZona();
    int opcion;

    do {
        System.out.println("\n--- Zona Urbana ---");
        System.out.println("1- Ver estado de la zona");
        System.out.println("2- Marcar sector");
        System.out.println("3- Ver estadisticas");
        System.out.println("4- Volver al menu principal");
        System.out.print("Seleccione una opcion: ");
        opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                mostrar_matriz();
                break;
            case 2:
                System.out.print("Fila (0-5): ");
                int fila = scan.nextInt();
                System.out.print("Columna (0-5): ");
                int columna = scan.nextInt();
                System.out.print("Estado (S/P/A): ");
                String estado = scan.next().toUpperCase();
                marcar_sector(fila, columna, estado);
                break;
            case 3:
                mostrar_estadisticas();
                break;
            case 4:
                System.out.println("Volviendo al menu principal...");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    } while (opcion != 4);
}
    
    static String[][] zona = new String[6][6];
    
    public static void inicializarZona() {
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 6; c++) {
                zona[f][c] = "S";
            }
        }
    }
    
    public static void mostrar_matriz(){
        System.out.println("\nEstado actual:");
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 6; c++) {
                System.out.print(zona[f][c] + " ");
            }
            System.out.println();
        }
    }

    public static void marcar_sector(int fila, int columna, String estado) {
        zona[fila][columna] = estado;

        if (estado.equals("A")) {
            int[][] direcciones = { {-1,0}, {1,0}, {0,-1}, {0,1} };
            for (int[] d : direcciones) {
                int f = fila + d[0];
                int c = columna + d[1];
                if (f >= 0 && f < 6 && c >= 0 && c < 6) {
                    if (zona[f][c].equals("S")) {
                        zona[f][c] = "P";
                    }
                }
            }
        }
    }

    public static void mostrar_estadisticas() {
        int s = 0, p = 0, a = 0;
        for (int f = 0; f < 6; f++) {
            for (int c = 0; c < 6; c++) {
                switch (zona[f][c]) {
                    case "S": s++; break;
                    case "P": p++; break;
                    case "A": a++; break;
                }
            }
        }
        System.out.println("S: " + s + ", P: " + p + ", A: " + a);
        System.out.println("Porcentaje en alerta: " + (a * 100 / 36) + "%");
    }

    public static void zonaUrbana() {
        Scanner sc = new Scanner(System.in);
        inicializarZona();

        while (true) {
            mostrar_matriz();
            mostrar_estadisticas();

            System.out.println("\n1. Marcar sector  |  2. Salir");
            int opcion = sc.nextInt();
            if (opcion == 2) break;

            System.out.print("Fila (0-5): ");
            int fila = sc.nextInt();
            System.out.print("Columna (0-5): ");
            int columna = sc.nextInt();
            System.out.print("Estado (S/P/A): ");
            String estado = sc.next().toUpperCase();

            marcar_sector(fila, columna, estado);
        }

        System.out.println("Fin del programa.");
        sc.close();
    }
}
    

