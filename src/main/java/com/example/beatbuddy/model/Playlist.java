package com.example.beatbuddy.model;

import java.util.LinkedList;
import java.util.Objects;

public class Playlist {
    private String nombre;
    private String descripcion;
    private LinkedList<Cancion> lista;

    public Playlist(String nombre, String descripcion, LinkedList<Cancion> lista) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lista = lista;
    }

    public Playlist(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lista = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LinkedList<Cancion> getLista() {
        return lista;
    }

    public void setLista(LinkedList<Cancion> lista) {
        this.lista = lista;
    }

    public void agregarCancion(Cancion nuevaCancion){
        this.lista.add(nuevaCancion);
    }

    public void eliminarCancion(Cancion cancionAEliminar){
        this.lista.remove(cancionAEliminar);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Playlist playlist)) return false;
        return Objects.equals(getNombre(), playlist.getNombre()) && Objects.equals(getDescripcion(), playlist.getDescripcion()) && Objects.equals(getLista(), playlist.getLista());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getDescripcion(), getLista());
    }
}
