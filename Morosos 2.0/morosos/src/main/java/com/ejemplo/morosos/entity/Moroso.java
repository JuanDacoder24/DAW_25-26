package com.ejemplo.morosos.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
 
@Entity
public class Moroso {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
 
    @Column(nullable = false)
    String nombre;
 
    @Column(nullable = false)
    String dni;
 
    @Column(nullable = false)
    double cantidad_adeudada;
 
    @Column(nullable = false)
    LocalDate fecha_alta;
 
    @Enumerated(EnumType.STRING) 
    @Column(nullable = false)
    Estado estado_pago;
 
    public enum Estado {
        PENDIENTE("PENDIENTE"),
        PAGADO("PAGADO");
 
        private final String estado;
 
        Estado(String estado){
            this.estado = estado;
        }
 
        public String getEstado(){
            return estado;
        }
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public String getDni() {
        return dni;
    }
 
    public void setDni(String dni) {
        this.dni = dni;
    }
 
    public double getCantidad_adeudada() {
        return cantidad_adeudada;
    }
 
    public void setCantidad_adeudada(double cantidad_adeudada) {
        this.cantidad_adeudada = cantidad_adeudada;
    }
 
    public LocalDate getFecha_alta() {
        return fecha_alta;
    }
 
    public void setFecha_alta(LocalDate fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
 
    public Estado getEstado_pago() {
        return estado_pago;
    }
 
    public void setEstado_pago(Estado estado_pago) {
        this.estado_pago = estado_pago;
    }
}