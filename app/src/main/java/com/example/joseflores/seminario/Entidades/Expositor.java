package com.example.joseflores.seminario.Entidades;

import java.io.Serializable;

public class Expositor implements Serializable {

    private String codigo;
    private String expositor;
    private String evaluador;
    private String asesor;
    private String maestria;
    private String doctorado;
    private String fecha;

    public Expositor() {
    }

    public Expositor(String codigo, String expositor, String evaluador, String asesor, String maestria, String doctorado, String fecha) {
        this.codigo = codigo;
        this.expositor = expositor;
        this.evaluador = evaluador;
        this.asesor = asesor;
        this.maestria = maestria;
        this.doctorado = doctorado;
        this.fecha = fecha;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public String getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(String evaluador) {
        this.evaluador = evaluador;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getMaestria() {
        return maestria;
    }

    public void setMaestria(String maestria) {
        this.maestria = maestria;
    }

    public String getDoctorado() {
        return doctorado;
    }

    public void setDoctorado(String doctorado) {
        this.doctorado = doctorado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
