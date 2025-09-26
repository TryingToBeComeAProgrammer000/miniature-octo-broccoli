package task_3_ds;

import java.util.ArrayList;
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
        System.out.println("Ingrese una opcion:\n1- Seguimiento de pedidos");
        int opcion = scan.nextInt();

        switch (opcion) {
            case 1:
                Pedidos();
                
                break;
            case 2:
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

    public static void Pedidos() {

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
        System.out.println("Total: " + montoGeneral);
        System.out.println("Promedio: " + montoPromedio);
        System.out.println("Tarjeta: " + tarjeta + "\nEfectivo: " + efectivo);
        System.out.println("Más caro: " + nombre.get(idMontoMax) + " == " + max);
        System.out.println("Más barato: " + nombre.get(idMontoMin) + " == " + min);

    }
    
    public static void reporte(List<String> nombre, List<Double> monto,List<String> metodoPago){
        
        System.out.println("**** Reporte por cliente ****");
        
        for (int clientes = 0; clientes < nombre.size(); clientes++) {
            System.out.println("");
            System.out.println("");
            System.out.println("Su nombre es: "+nombre.get(clientes));
            System.out.println("El monto a pagar es de: "+monto.get(clientes));
            System.out.println("Usted eligio pagar con: "+metodoPago.get(clientes));
            
        }
    
    }
}
