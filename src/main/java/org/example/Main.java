package org.example;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.ListIterator;
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

        // 'actual' va a guardar la FACTURA que estamos viendo en este momento.
        // Antes usabas 'posicion' (un número/índice), ahora usamos directamente
        // el objeto Factura. Esto es clave para aprovechar el ListIterator.
        Factura actual = null;

        // El ListIterator es una herramienta especial de Java para recorrer listas.
        // A diferencia de un 'for' normal, el ListIterator puede moverse
        // hacia adelante (next) y hacia atrás (previous) sin tener que
        // recorrer la lista desde el principio cada vez.
        // Piensa en él como un "cursor" o un dedo que se mueve sobre la lista.
        ListIterator<Factura> it = null;

        System.out.println("======== COMO DESEA BUSCAR LA FACTURA ========");
        System.out.println("1. Por ID de factura");
        System.out.println("2. Por ID de cliente");
        System.out.print("Seleccione una opción: ");
        option3 = leerEntero();

        switch (option3) {
            case 1 -> {
                System.out.print("Ingrese ID de la factura: ");
                Long idFactura = leerLong();

                // Creamos el iterador apuntando al inicio de la lista.
                it = listaFactura.listIterator();

                // hasNext() pregunta: "¿hay un elemento más adelante?"
                // Si sí, entramos al bucle.
                while (it.hasNext()) {
                    // next() hace DOS cosas a la vez:
                    // 1) Devuelve el siguiente elemento de la lista.
                    // 2) Mueve el cursor una posición hacia adelante.
                    Factura f = it.next();

                    if (f.getId().equals(idFactura)) {
                        // Encontramos la factura buscada, la guardamos
                        // y salimos del bucle con 'break'.
                        actual = f;
                        break;
                    }
                }
                // OJO: esto es UN SOLO recorrido de la lista (recorre cada
                // elemento máximo una vez). Antes, con 'get(i)' dentro de un
                // 'for', cada get(i) YA recorría la lista desde el inicio,
                // así que terminabas recorriendo la lista dentro de otro
                // recorrido = mucho más trabajo del necesario.
            }
            case 2 -> {
                System.out.print("Ingrese ID del cliente: ");
                Long idCliente = leerLong();
                it = listaFactura.listIterator();
                while (it.hasNext()) {
                    Factura f = it.next();
                    if (f.getIdCliente().equals(idCliente)) {
                        actual = f;
                        break;
                    }
                }
            }
            default -> {
                System.out.println("Opción no válida.");
                return;
            }
        }

        // Si 'actual' sigue siendo null, es porque el bucle nunca encontró
        // una factura que cumpliera la condición.
        if (actual == null) {
            System.out.println("No se encontró ninguna factura.");
            return;
        }

        boolean mostrar = true;
        while (true) {
            if (mostrar) {
                // Mostramos la factura actual (ya no usamos listaFactura.get(posicion))
                actual.mostrarFactura();
                mostrar = false;
            }

            System.out.println("1. Ver anterior");
            System.out.println("2. Ver siguiente");
            System.out.println("3. Volver");
            System.out.print("Seleccione una opción: ");
            option4 = leerEntero();

            switch (option4) {
                case 1 -> {
                    // el iterador  está PARADO ENTRE
                    // dos elementos de la lista, no encima de uno.
                    // Después del código de búsqueda de arriba, el cursor
                    // quedó justo DESPUÉS de 'actual' (porque next() lo mueve
                    // hacia adelante al devolver el elemento).

                    // hasPrevious() pregunta: "¿hay algo detrás del cursor?"
                    if (it.hasPrevious()) {
                        // La PRIMERA llamada a previous() nos devuelve el
                        // elemento que está detrás del cursor, que en este
                        // punto es el mismo 'actual' que ya teníamos!
                        // (porque el cursor estaba justo después de él).
                        it.previous();
                        // Por eso preguntamos hasPrevious() OTRA VEZ:
                        // para saber si detrás de 'actual' hay un elemento
                        // REAL anterior (el que de verdad queremos mostrar).
                        if (it.hasPrevious()) {
                            // La SEGUNDA llamada a previous() sí nos da
                            // el elemento anterior de verdad.
                            actual = it.previous();
                            // Ahora el cursor quedó ANTES de 'actual'.
                            // Llamamos next() para dejarlo justo DESPUÉS
                            // de 'actual' otra vez, y así la próxima vez
                            // que pidamos "anterior" o "siguiente" funcione igual.
                            it.next();
                            mostrar = true;
                        } else {
                            // No había un anterior real. Como ya movimos
                            // el cursor con el primer previous(), lo
                            // regresamos a su lugar con next().
                            it.next();
                            System.out.println("Ya está en la primera factura.");
                        }
                    } else {
                        System.out.println("Ya está en la primera factura.");
                    }
                }
                case 2 -> {
                    // "Siguiente" es mucho más simple:
                    // solo preguntamos si hay algo más adelante y avanzamos.
                    if (it.hasNext()) {
                        actual = it.next();
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