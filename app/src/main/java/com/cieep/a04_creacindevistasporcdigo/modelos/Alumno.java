package com.cieep.a04_creacindevistasporcdigo.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Alumno implements Parcelable {

    private String nombre;
    private String apellidos;
    private String ciclo;
    private char grupo;

    public Alumno(String nombre, String apellidos, String ciclo, char grupo) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ciclo = ciclo;
        this.grupo = grupo;
    }

    protected Alumno(Parcel in) {
        nombre = in.readString();
        apellidos = in.readString();
        ciclo = in.readString();
        grupo = (char) in.readInt();
    }

    public static final Creator<Alumno> CREATOR = new Creator<Alumno>() {
        @Override
        public Alumno createFromParcel(Parcel in) {
            return new Alumno(in);
        }

        @Override
        public Alumno[] newArray(int size) {
            return new Alumno[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public char getGrupo() {
        return grupo;
    }

    public void setGrupo(char grupo) {
        this.grupo = grupo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nombre);
        parcel.writeString(apellidos);
        parcel.writeString(ciclo);
        parcel.writeInt((int) grupo);
    }
}
