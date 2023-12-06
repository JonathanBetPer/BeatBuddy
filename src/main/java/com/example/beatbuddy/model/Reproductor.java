package com.example.beatbuddy.model;

import javazoom.jlgui.basicplayer.*;

import java.io.BufferedInputStream;
import java.util.LinkedList;
import java.util.Map;

public class Reproductor extends Thread implements BasicPlayerListener {

    private BasicPlayer reproductor;
    private Cancion cancionActual;
    private LinkedList<Cancion> historialCanciones;
    private LinkedList<Cancion> colaCanciones;
    private long songLengthInMicroseconds;

    private boolean isRepeat;


    public Reproductor(Cancion cancionActual, LinkedList<Cancion> historialCanciones) {
        this.reproductor = new BasicPlayer();
        this.reproductor.addBasicPlayerListener(this);
        this.cancionActual = cancionActual;
        if (historialCanciones == null) {
            this.historialCanciones = new LinkedList<>();
        } else {
            this.historialCanciones = historialCanciones;
        }
        this.colaCanciones = new LinkedList<>();
        this.isRepeat = false;
    }

    public void setCancionActual(Cancion cancionActual) {
        this.cancionActual = cancionActual;
    }


    public void comenzar() {

        if (cancionActual == null) {
            if (!colaCanciones.isEmpty()) {
                cancionActual = colaCanciones.poll();
            } else {
                System.out.println("No hay canciones en la cola");
                return;
            }
        }






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
        if (!colaCanciones.isEmpty()) {
            historialCanciones.addFirst(cancionActual);
            cancionActual = colaCanciones.poll();
            try {
                sleep(500);

            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            comenzar();
        }
    }

    public boolean estaReproduciendo() {
        return reproductor.getStatus() == BasicPlayer.PLAYING;
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

    public void agregarPlaylistCola(LinkedList<Cancion> listaCanciones) {
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

    @Override
    public void opened(Object o, Map map) {

    }


    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        this.songLengthInMicroseconds = microseconds;
    }

    public long getSongLengthInMicroseconds() {
        return songLengthInMicroseconds;
    }

    @Override
    public void stateUpdated(BasicPlayerEvent basicPlayerEvent) {
        //EOM = End of Media
        if (basicPlayerEvent.getCode() == BasicPlayerEvent.EOM) {

            if (isRepeat) {
              comenzar();
            } else {
                avanzarCancion();
            }

        }
    }

    @Override
    public void setController(BasicController basicController) {

    }
}
