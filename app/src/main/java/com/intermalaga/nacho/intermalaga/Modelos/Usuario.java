package com.intermalaga.nacho.intermalaga.Modelos;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Usuario implements Serializable {

    @Expose
    @SerializedName("idUsuario")
    private String idUsuario;

    @Expose
    @SerializedName("nombre")
    private String nombre;

    @Expose
    @SerializedName("correo")
    private String correo;

    public Usuario() {  }

    public Usuario(String idUsuario, String nombre, String correo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    ///////////////////
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    ///////////////////
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    ///////////////////

}