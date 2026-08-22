package org.example;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList; //libreria de las listas enlazadas dobles
import java.time.LocalDateTime; //libreria para la fecha y horax
public class Main {
    static Scanner leer = new Scanner(System.in);
    static List<Factura> listaFactura = new LinkedList<Factura>();
    public static void main(String[] args) {
        int option;
        do {
            option = menu();
            switch (option) {
                case 1 -> crearFactura();
                case 2 -> eliminarFactura();
                case 3 -> verFacturas();
                case 4 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 4);
    }
    public static int menu(){
        int option;
        System.out.println("========= SISTEMA DE FACTURACIÓN =========");
        System.out.println("1. Crear factura");
        System.out.println("2. Eliminar factura");
        System.out.println("3. Ver facturas");
        System.out.println("4. Salir");
        option=leer.nextInt();
        return option;
    }
    public static void crearFactura(){
        leer.nextLine();// limpia el buffer es decir que antes habia un leer next int y puede generar que no nos deje poner mas valorees
        System.out.print("Ingrese producto: ");
        String producto = leer.nextLine();
        System.out.print("Ingrese cantidad: ");
        Double cantidad = leer.nextDouble();
        System.out.println("Ingrese documento del cliente");
        Long idCliente = leer.nextLong();
        System.out.print("Ingrese precio: ");
        Double precio = leer.nextDouble();
        listaFactura.add(new Factura(producto,idCliente, cantidad, precio));
        System.out.println("Factura creada correctamente.");
    }
    public static void eliminarFactura(){
        System.out.print("Ingrese el ID de la factura a eliminar: ");
        Long id = leer.nextLong();
        leer.nextLine();
        boolean eliminada = listaFactura.removeIf(factura -> factura.getId().equals(id));
        if (eliminada) {
            System.out.println("Factura eliminada correctamente.");
        } else {
            System.out.println("No se encontró una factura con ese ID.");
        }
    }
    public static void verFacturas() {
        int option3;
        int option4;
        int posicion = -1;
        System.out.println("======== COMO DESEA BUSCAR LA FACTURA ========");
        System.out.println("1. Por ID de factura");
        System.out.println("2. Por ID de cliente");
        System.out.print("Seleccione una opción: ");
        option3 = leer.nextInt();
        switch (option3) {
            case 1 -> {
                System.out.print("Ingrese ID de la factura: ");
                Long idFactura = leer.nextLong();
                for (int i = 0; i < listaFactura.size(); i++) {
                    if (listaFactura.get(i).getId().equals(idFactura)) {
                        posicion = i;
                        break;
                    }
                }
            }
            case 2 -> {
                System.out.print("Ingrese ID del cliente: ");
                Long idCliente = leer.nextLong();
                for (int i = 0; i < listaFactura.size(); i++) {
                    if (listaFactura.get(i).getIdCliente().equals(idCliente)) {
                        posicion = i;
                        break;
                    }
                }
            }
            default -> {
                System.out.println("Opción no válida.");
                return;
            }
        }
        // Si no encontró ninguna factura
        if (posicion == -1) {
            System.out.println("No se encontró ninguna factura.");
            return;
        }
        // para esto es la posicion para avanzar en cada una y poder ver la siguiente o la anterio
        while (true) {

            listaFactura.get(posicion).mostrarFactura();

            System.out.println("1. Ver anterior");
            System.out.println("2. Ver siguiente");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");
            option4 = leer.nextInt();
            switch (option4) {
                case 1 -> {
                    if (posicion > 0) {
                        posicion--;
                    } else {
                        System.out.println("Ya está en la primera factura.");
                    }
                }
                case 2 -> {
                    if (posicion < listaFactura.size() - 1) {
                        posicion++;
                    } else {
                        System.out.println("Ya está en la última factura.");
                    }
                }
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }
}
