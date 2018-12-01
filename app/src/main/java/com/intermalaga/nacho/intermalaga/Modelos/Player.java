package com.intermalaga.nacho.intermalaga.Modelos;

import java.io.Serializable;

public class Player implements Serializable {

    private String idMDB ;
    private String name ;
    private String foto ;
    private String posicion ;
    private String grito = "Hola soy del InterMalaga";
    private String category;
    private String id;


    public Player(String idMDB, String name, String posicion, String category, String id) {
        this.idMDB = idMDB;
        this.name = name;
        this.posicion = posicion;
        this.category = category;
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGrito() {
        return grito;
    }

    public void setGrito(String grito) {
        this.grito = grito;
    }

    public Player(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdMDB() {
        return idMDB;
    }

    public String getName() {
        return name;
    }

    public String getFoto() {
        return foto;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setIdMDB(String idMDB) {
        this.idMDB = idMDB;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return this.grito ;
    }
}
