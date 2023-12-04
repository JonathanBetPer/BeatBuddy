package com.example.beatbuddy.model;

import java.io.File;

public class Cancion {

    private int ID;
    private String nombre;
    private String autor;

    private String letra;
    private File archivo;

    public Cancion(int ID, String nombre, String autor, String letra, File archivo) {
        this.ID = ID;
        this.nombre = nombre;
        this.letra = letra;
        this.autor = autor;
        this.archivo = archivo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setRutaArchivo(File archivo) {
        this.archivo = archivo;
    }


}
