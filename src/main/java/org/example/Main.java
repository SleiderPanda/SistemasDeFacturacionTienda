package org.example;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList; //libreria de las listas enlazadas

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
        option=leerEntero();
        return option;
    }
    public static void crearFactura() {
        leer.nextLine();
        System.out.print("Ingrese documento del cliente: ");
        Long idCliente = leerLong();
        leer.nextLine();
        Factura factura = new Factura(idCliente);
        int continuar;
        boolean booleanContinua = true;
        do {
            System.out.print("Ingrese producto: ");
            String producto = leer.nextLine();
            System.out.print("Ingrese cantidad: ");
            Double cantidad = leerDouble();
            System.out.print("Ingrese precio unitario: ");
            Double precioUnit = leerDouble();
            factura.agregarProducto(producto, cantidad, precioUnit);
            leer.nextLine();
            System.out.println("¿Desea agregar otro producto?");
            System.out.println("1. si");
            System.out.println("2. no");
            continuar = leerEntero();
            leer.nextLine();
            switch (continuar){
                case 1 -> System.out.println("ok continuar");
                case 2 -> booleanContinua = false;
                default -> System.out.println("Opción no válida.");
            }
        } while (booleanContinua);
        listaFactura.add(factura);
        System.out.println("Factura creada correctamente.");
    }
    public static void eliminarFactura(){
        System.out.print("Ingrese el ID de la factura a eliminar: ");
        Long id = leerLong();
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
        option3 = leerEntero();
        switch (option3) {
            case 1 -> {
                System.out.print("Ingrese ID de la factura: ");
                Long idFactura = leerLong();
                for (int i = 0; i < listaFactura.size(); i++) {
                    if (listaFactura.get(i).getId().equals(idFactura)) {
                        posicion = i;
                        break;
                    }
                }
            }
            case 2 -> {
                System.out.print("Ingrese ID del cliente: ");
                Long idCliente = leerLong();
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
        if (posicion == -1) {
            System.out.println("No se encontró ninguna factura.");
            return;
        }
        boolean mostrar = true;
        while (true) {
            if (mostrar) {
                listaFactura.get(posicion).mostrarFactura();
                mostrar = false;
            }
            System.out.println("1. Ver anterior");
            System.out.println("2. Ver siguiente");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");
            option4 = leerEntero();
            switch (option4) {
                case 1 -> {
                    if (posicion > 0) {
                        posicion--;
                        mostrar = true;
                    } else {
                        System.out.println("Ya está en la primera factura.");
                    }
                }
                case 2 -> {
                    if (posicion < listaFactura.size() - 1) {
                        posicion++;
                        mostrar = true;
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
    public static int leerEntero() {
        while (!leer.hasNextInt()) {
            System.out.println("Error: debe ingresar un número entero.");
            leer.next();
        }
        return leer.nextInt();
    }
    public static Long leerLong() {
        while (!leer.hasNextLong()) {
            System.out.println("Error: debe ingresar un número.");
            leer.next();
        }
        return leer.nextLong();
    }
    public static Double leerDouble() {
        while (!leer.hasNextDouble()) {
            System.out.println("Error: debe ingresar un número.");
            leer.next();
        }
        return leer.nextDouble();
    }
}