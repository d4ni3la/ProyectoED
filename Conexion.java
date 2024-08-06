package com.mycompany.proyectoed2;

/**
 *
 * @author danielaperez
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author danielaperez
 */
public class Conexion {
    Connection conectar= null;
    String usuario= "parker";
    String contrasenia= "Pker050455";
    String bd= "trenes";
    String ip= "localhost";
    String puerto= "3306";
    
    String cadena= "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conectar= DriverManager.getConnection(cadena,usuario,contrasenia);
            //JOptionPane.showMessageDialog(null, "Se conecto correctamente a la base de datos");
            System.out.println("\tsConexion establecida");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se conecto a la base de datos, error: "+ e.toString());
        }
        
        return conectar;
    }
}
