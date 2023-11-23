package com.example.beatbuddy.model;

import javazoom.jlgui.basicplayer.BasicPlayer;

import java.util.LinkedList;

public class Reproductor extends Thread{

    private BasicPlayer reproductor;
    private Cancion cancionActual;
    private LinkedList<Cancion> historialCanciones;
    private LinkedList<Cancion> colaCanciones;

    public Reproductor(Cancion cancionActual, LinkedList<Cancion> historialCanciones, LinkedList<Cancion> colaCanciones) {
        this.reproductor = new BasicPlayer();
        this.cancionActual = cancionActual;
        this.historialCanciones = historialCanciones;
        this.colaCanciones = new LinkedList<>();
    }

    public BasicPlayer getReproductor() {
        return reproductor;
    }




}
