package com.example.beatbuddy.model;

import java.io.File;
import java.util.LinkedList;

public class Usuario {
    private int ID;
    private String nombreUsuario;
    private String nombre;
    private String correo;
    private File imagen;
    private LinkedList<Playlist> listaPlaylists;

    public Usuario(int ID, String nombreUsuario, String nombre, String correo, File imagen) {
        this.ID = ID;
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.correo = correo;
        this.imagen = imagen;
        this.listaPlaylists = new LinkedList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    public LinkedList<Playlist> getListaPlaylists() {
        return listaPlaylists;
    }

    public void setListaPlaylists(LinkedList<Playlist> listaPlaylists) {
        this.listaPlaylists = listaPlaylists;
    }

    public void crearPlaylist(String nombre, String descripcion){
        this.listaPlaylists.add(new Playlist(nombre, descripcion));
    }

    public void editarDatoslaylist(Playlist playlist,String nombre, String descripcion){
        if (this.listaPlaylists.contains(playlist)){
            for (Playlist lista:listaPlaylists) {
                if (lista.equals(playlist)){
                    lista.setNombre(nombre);
                    lista.setDescripcion(descripcion);
                    break;
                }
            }
        }
    }

    public void eliminarPlayylist(Playlist playlist){
        if (this.listaPlaylists.contains(playlist)){
           listaPlaylists.remove(playlist);
        }
    }

    public void agregarCancionPlaylist(Playlist playlist, Cancion cancion){
        if (this.listaPlaylists.contains(playlist)){
            for (Playlist lista:listaPlaylists) {
                if (lista.equals(playlist)){
                    lista.agregarCancion(cancion);
                }
            }
        }
    }

    public void eliminarCancionPlaylist(Playlist playlist, Cancion cancion){
        if (this.listaPlaylists.contains(playlist)){
            for (Playlist lista:listaPlaylists) {
                if (lista.equals(playlist)){
                    lista.eliminarCancion(cancion);
                }
            }
        }
    }

}
