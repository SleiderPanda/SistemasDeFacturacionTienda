package org.example;

import lombok.Getter;

@Getter
public class Producto {

    private String nombre;
    private Double cantidad;
    private Double precioUnit;
    private Double precioTotal;

    public Producto(String nombre, Double cantidad, Double precioUnit) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnit = precioUnit;
        this.precioTotal = cantidad * precioUnit;
    }

    public void mostrarProducto() {
        System.out.println("Producto: " + nombre);
        System.out.println("Cantidad: " + cantidad);
        System.out.println("Precio unitario: $" + precioUnit);
        System.out.println("Precio total: $" + precioTotal);
    }
}