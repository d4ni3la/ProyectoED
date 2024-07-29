

package com.mycompany.conbd;
import java.sql.Connection;
/**
 *
 * @author danielaperez
 */
public class ConBD {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        CConexion objetoConexion= new CConexion();
        // objetoConexion.estableceConexion();
        Connection conexion = objetoConexion.estableceConexion();
        
        consultas objetoConsultas = new consultas(conexion);
        objetoConsultas.buscarEstacion("División del Norte");
    }
}
