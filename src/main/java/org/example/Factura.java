package org.example;

import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
@Getter
public class Factura {
    private static Long contador = 0L;
    private Long id;
    private Long idCliente;
    private List<Producto> productos;
    private LocalDateTime fechaDecompra;
    public Factura(Long idCliente) {
        this.id = ++contador;
        this.idCliente = idCliente;
        this.productos = new LinkedList<>();
        this.fechaDecompra = LocalDateTime.now();
    }
    public void agregarProducto(String nombre, Double cantidad, Double precioUnit) {
        Producto producto = new Producto(nombre, cantidad, precioUnit);
        productos.add(producto);
    }

    public void mostrarFactura() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("========== FACTURA ==========");
        System.out.println("ID: " + id);
        System.out.println("ID cliente: " + idCliente);
        System.out.println("Fecha de compra: " + fechaDecompra.format(formato));
        System.out.println("\n========== PRODUCTOS ==========");
        for (Producto producto : productos) {
            producto.mostrarProducto();
            System.out.println("-----------------------------");
        }
        System.out.println("TOTAL FACTURA: $" + calcularTotal());
        System.out.println("=============================");
    }
    public Double calcularTotal() {
        Double total = 0.0;
        for (Producto producto : productos) {
            total += producto.getPrecioTotal();
        }
        return total;
    }
}