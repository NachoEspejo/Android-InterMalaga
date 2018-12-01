package com.intermalaga.nacho.intermalaga.Modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Programación multimedia y dispositivos móviles
 * @author Antonio J.Sánchez
 * @year 2018/19
 *
 */
public class PlayerList {

    private int idPlayerList;
    private String nombre = "";

    public PlayerList(int id) {
        this.idPlayerList = id;
    }

    public PlayerList(int id, String name) {
        idPlayerList = id;
        nombre = name;
    }

    public int getIdPlayerLista() {
        return idPlayerList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}