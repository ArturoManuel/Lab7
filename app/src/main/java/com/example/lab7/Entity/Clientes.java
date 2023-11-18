package com.example.lab7.Entity;

import com.google.firebase.Timestamp;

import java.util.Date;

public class Clientes {

    String nombre ;
    String autHID;

    String rol;

    Timestamp cita ;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutHID() {
        return autHID;
    }

    public void setAutHID(String autHID) {
        this.autHID = autHID;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Timestamp getCita() {
        return cita;
    }

    public void setCita(Timestamp cita) {
        this.cita = cita;
    }
}
