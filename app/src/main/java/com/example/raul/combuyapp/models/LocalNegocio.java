package com.example.raul.combuyapp.models;

/**
 * Created by raul on 24/04/18.
 */

public class LocalNegocio {
    private int idnegocio;
    private float latitud;
    private float longitud;
    private String descripcion;

    public LocalNegocio(int idnegocio, float latitud, float longitud, String descripcion) {
        this.idnegocio = idnegocio;
        this.latitud = latitud;
        this.longitud = longitud;
        this.descripcion = descripcion;
    }

    public LocalNegocio(){}

    public int getIdnegocio() {
        return idnegocio;
    }

    public void setIdnegocio(int idnegocio) {
        this.idnegocio = idnegocio;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
