package com.example.beatbuddy.model;

import java.util.LinkedList;
import java.util.Objects;

public class Playlist {
    private int ID;
    private String nombre;
    private String descripcion;
    private int idUsuario;
    private LinkedList<Cancion> lista;

    public Playlist(int ID, String nombre, String descripcion, int idUsuario, LinkedList<Cancion> lista) {
        this.ID = ID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
        this.lista = lista;
    }

     public Playlist(int ID, String nombre, String descripcion, int idUsuario) {
          this.ID = ID;
          this.nombre = nombre;
          this.descripcion = descripcion;
          this.idUsuario = idUsuario;
          this.lista = new LinkedList<>();
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
