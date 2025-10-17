package com.jrr.alquiler.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;
    private String marca;
    private String modelo;
    private int anio;
    private String color;
    private String estado;

    public Vehiculo() {}

    public Vehiculo(String placa, String marca, String modelo, int anio, String color, String estado) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.color = color;
        this.estado = estado;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
