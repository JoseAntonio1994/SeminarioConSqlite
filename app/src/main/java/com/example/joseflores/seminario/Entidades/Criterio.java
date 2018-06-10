package com.example.joseflores.seminario.Entidades;

import java.io.Serializable;

public class Criterio implements Serializable{

    private String descripcion;
    private int puntaje;

    public Criterio() {
    }

    public Criterio(String descripcion, int puntaje) {
        this.descripcion = descripcion;
        this.puntaje = puntaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
