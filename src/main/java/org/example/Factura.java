package org.example;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;; //libreria para la fecha y horax

@Getter
public class Factura {
    //parametros a usar
    private static Long contador = 0L; //contador para que la id se haga de forma auto
    private Long id;
    private Long idCliente;
    private String producto;
    private Double cantidad;
    private Double precio;
    private LocalDateTime fechaDecompra;


    public Factura(String producto, Long idCliente, Double cantidad, Double precio) { //se ponen solo los datos que necesitamos recibir porque id es con contador y fecha es con la del pc por ende no hay que poner ninguna de las 2
        this.id = ++contador; // id con el contador el cual se pone auto
        this.idCliente = idCliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaDecompra = LocalDateTime.now(); //fecha con la del pc se pone auto
    }

    public void mostrarFactura() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("========== FACTURA ==========");
        System.out.println("ID: " + id);
        System.out.println("Producto: " + producto);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio: $" + precio);
        System.out.println("Fecha de compra: " + fechaDecompra.format(formato));
        System.out.println("=============================");
    }
}
