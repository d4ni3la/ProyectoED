package com.mycompany.proyectoed2;

import java.util.ArrayList;

public class ProyectoED2 {

    public static void main(String[] args) {
        try {
            algoritmoRuta ruta = new algoritmoRuta();
            ArrayList<Registro> busqueda = new ArrayList<>();
            
            ruta.busqueda(busqueda);
            //ruta.mismaLinea("2", "6", "4", true);
            //System.out.println(ruta.nodoCercano("1", "2"));
            
        } catch (Exception e) {
            // Manejo de la excepción
            System.err.println("Se ha producido un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
