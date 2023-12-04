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

    public Reproductor(Cancion cancionActual, LinkedList<Cancion> historialCanciones) {
        this.reproductor = new BasicPlayer();
        this.cancionActual = cancionActual;
        this.historialCanciones = historialCanciones;
        this.colaCanciones = new LinkedList<>();
    }

    public void setCancionActual(Cancion cancionActual) {
        this.cancionActual = cancionActual;
    }


    public void comenzar() {

        try {
                if (cancionActual.getArchivo() != null) {
                    reproductor.open(cancionActual.getArchivo());
                    reproductor.play();
                } else {
                    System.out.println("Error al leer el archivo");
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

    public void avanzarCancion() {
        historialCanciones.addFirst(cancionActual);
        cancionActual = colaCanciones.poll();
        try {
            sleep(500);

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        comenzar();
    }

    public void retrocederCancion() {
        colaCanciones.addFirst(cancionActual);
        cancionActual = historialCanciones.poll();
        try {
            sleep(500);

        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        comenzar();
    }

    public void agregarCancionCola(Cancion cancion) {
        colaCanciones.add(cancion);
    }

    public void agregarCancionCola(LinkedList<Cancion> listaCanciones) {
        colaCanciones.addAll(listaCanciones);
    }

    public void shuffleCola() {
        LinkedList<Cancion> colaAux = new LinkedList<>();
        int random;
        while (!colaCanciones.isEmpty()) {
            random = (int) (Math.random() * colaCanciones.size());
            colaAux.add(colaCanciones.get(random));
            colaCanciones.remove(random);
        }
        colaCanciones = colaAux;
    }

    public void limpiarCola() {
        colaCanciones.clear();
    }

}
