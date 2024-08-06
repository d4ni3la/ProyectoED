package com.mycompany.proyectoed2;

public class Registro {
    //Conexion conexion;
    String nombre;
    String IdL;
    String IdE;
    String tipo;

    public Registro(/*Conexion conexion,*/ String nombre, String IdL, String IdE, String tipo) {
        //this.conexion = conexion;
        this.nombre = nombre;
        this.IdL = IdL;
        this.IdE = IdE;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIdL() {
        return IdL;
    }

    public String getIdE() {
        return IdE;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdL(String IdL) {
        this.IdL = IdL;
    }

    public void setIdE(String IdE) {
        this.IdE = IdE;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
    @Override
    public String toString(){
        return "\nNombre: "+ nombre+ "\t\tIdL: "+ IdL+ "  IdE: "+ 
                IdE+ "\t\t Tipo: "+ tipo;
    }
}