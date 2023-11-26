package com.example.beatbuddy.model;

import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;

import java.io.BufferedInputStream;
import java.util.LinkedList;

public class Reproductor extends Thread{

    private BasicPlayer reproductor;
    private Cancion cancionActual;
    private LinkedList<Cancion> historialCanciones;
    private LinkedList<Cancion> colaCanciones;

    public Reproductor(Cancion cancionActual, LinkedList<Cancion> historialCanciones, LinkedList<Cancion> colaCanciones) {
        this.reproductor = new BasicPlayer();
        this.cancionActual = null;
        this.historialCanciones = historialCanciones;
        this.colaCanciones = new LinkedList<>();
    }

    public void setCancionActual(Cancion cancionActual) {
        this.cancionActual = cancionActual;
    }


    public void comenzar(BufferedInputStream bufferedInputStream) {

        try {
            if (cancionActual!=null) {
                if (bufferedInputStream != null) {
                    reproductor.open(bufferedInputStream);
                    reproductor.play();
                } else {
                    System.out.println("Error al leer el archivo");
                }
            } else {
                System.out.println("Ruta vacía");
            }
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }

    }

    public void pausar() {
        try {
            reproductor.pause();
        } catch ( BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    public void continuar() {
        try {
            reproductor.resume();
        } catch ( BasicPlayerException e) {
            e.printStackTrace();
        }
    }

    public void parar() {
        try {
            reproductor.stop();
        } catch ( BasicPlayerException e) {
            e.printStackTrace();
        }
    }


    public BasicPlayer getReproductor() {
        return reproductor;
    }






}
