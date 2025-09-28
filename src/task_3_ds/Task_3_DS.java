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
        System.out.println("Ingrese una opcion:\n1- Seguimiento de pedidos\n2- Gestor de pacientes");
        int opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                pedidos();
                
                break;
            case 2:
                gestionPacientes();
                break;
            case 3:
                opcion = scan.nextInt();
                switch (opcion) {
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    default:

                }
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
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
    
}
