package com.mycompany.proyectoed2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class alanCode {

    Scanner scan = new Scanner(System.in);

    public void busqueda(ArrayList busqueda) {
        String idLinA = null;
        String idLinB = null;
        String idEstA = null;
        String idEstB = null;
        String nombreA = null;
        String nombreB = null;
        String tipoA = null;
        String tipoB = null;

        ArrayList nodoCruce = new ArrayList();
        Conexion conexion = new Conexion();
        try (Connection con = conexion.estableceConexion()) {
            if (con == null) {
                System.out.println("No se pudo establecer la conexión.");
                return;
            }

            //INGRESA VALORES PARA LA BUSQUEDA
            System.out.println("Ingresa el nombre de la estación en la que se encuentra: ");
            String puntoA = scan.nextLine();
            System.out.println("Ingresa el nombre de la estación destino: ");
            String puntoB = scan.nextLine();

            //BUSQUEDA DE ESTACION
            String query = "SELECT * FROM registro WHERE nombre = ?";

            // Consulta para el puntoA
            try (PreparedStatement stmtA = con.prepareStatement(query)) {
                stmtA.setString(1, puntoA);
                ResultSet rsA = stmtA.executeQuery();

                if (rsA.next()) { // Verifica si hay resultados
                    nombreA = rsA.getString("nombre");
                    idLinA = rsA.getString("idL");
                    idEstA = rsA.getString("idE");
                    tipoA = rsA.getString("tipo");
                    busqueda.add(new Registro(nombreA, idLinA, idEstA, tipoA));
                } else {
                    System.out.println("No se encontró ninguna estación con el nombre: " + puntoA);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Consulta para el puntoB
            try (PreparedStatement stmtB = con.prepareStatement(query)) {
                stmtB.setString(1, puntoB);
                ResultSet rsB = stmtB.executeQuery();

                if (rsB.next()) { // Verifica si hay resultados
                    nombreB = rsB.getString("nombre");
                    idLinB = rsB.getString("idL");
                    idEstB = rsB.getString("idE");
                    tipoB = rsB.getString("tipo");
                    busqueda.add(new Registro(nombreB, idLinB, idEstB, tipoB));
                } else {
                    System.out.println("No se encontró ninguna estación con el nombre: " + puntoB);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            /*
            //Seleccion de funciones-----------------------------
            //If para valiidar si el nodo es un cruce
            if (tipoA.equals("1")) {
                boolean salirCruce = true;
                do {
                    String query2 = "SELECT * FROM registro WHERE nombre = ? ";
                    try (PreparedStatement stmt2 = con.prepareStatement(query2)) {
                        stmt2.setString(1, nombreA);
                        ResultSet rs2 = stmt2.executeQuery();
                        while (rs2.next()) {
                            if ((rs2.getString("tipo")).contains("1")) {
                                nodoCruce.add(idLinA);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } while (salirCruce == false);
                System.out.println(nodoCruce);

            } else if (tipoB.equals("1")) {
                boolean salirCruce = true;
                do {
                    String query2 = "SELECT * FROM registro WHERE nombre = ?";
                    try (PreparedStatement stmt2 = con.prepareStatement(query2)) {
                        stmt2.setString(1, nombreA);
                        ResultSet rs2 = stmt2.executeQuery();

                        if (rs2.next()) { // Verifica si hay resultados
                            idLinB = rs2.getString("idL");
                            nodoCruce.add(idLinB);
                        } else {
                            System.out.println("No hay mas registros");
                            salirCruce = false;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } while (salirCruce == true);

                System.out.println(nodoCruce);
            }
             */
            //SELECCION DE ALGORITOMO--------------------------------------
            if (idLinA.equals(idLinB)) {
                mismaLinea(idEstA, idEstB, idLinA);
            } else {
                //No pertenecen a la mima linea
                if (nodoCercano(idEstA, idLinA).equals(nodoCercano(idEstB, idLinB))) {
                    String noEstacionA = null;
                    String noEstacionB = null;
                    String queryA = "Select * from registro where nombre = ? and idL = ?";
                     try (PreparedStatement buscarEsta = con.prepareStatement(queryA)) {
                        String nomQuery =  nodoCercano(idEstA, idLinA);
                        buscarEsta.setString(1, nomQuery);
                        buscarEsta.setString(2, idLinA);
                        
                        ResultSet rsNodoA = buscarEsta.executeQuery();
                        
                        noEstacionA = rsNodoA.getString("idE");
                        System.out.println("EL nodo de Medrano: "+rsNodoA.getString("idE"));
                    }catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            }
                //Ultimo catch---------------------------------
            }catch (SQLException e) {
            e.printStackTrace();
        }
        }
        //Obtiene la direccion a donde debe de ir la persona
    public String direccionA(String idLin, int direccionATomar) {
        String direccion = null;
        ArrayList estaciones = new ArrayList();
        boolean salir = true;
        int nodoInt = 1;

        Conexion conexion = new Conexion();
        try (Connection cone = conexion.estableceConexion()) {
            if (cone == null) {
                System.out.println("No se pudo establecer la conexión.");
            }
            do {
                String nodo = String.valueOf(nodoInt);
                String query2 = "SELECT * FROM registro WHERE idL = ? AND idE = ?";
                try (PreparedStatement estacion = cone.prepareStatement(query2)) {
                    estacion.setString(1, idLin);
                    estacion.setString(2, nodo);
                    ResultSet rsA = estacion.executeQuery();

                    if (rsA.next()) { // Verifica si hay resultados
                        String nombreA = rsA.getString("nombre");
                        estaciones.add(nombreA);
                    } else {
                        salir = false;
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                nodoInt++;
            } while (salir);

            switch (direccionATomar) {
                case 1:
                    //Va Decrementando
                    direccion = (String) estaciones.get(0);
                    break;
                case 2:
                    //Va Incrementando
                    direccion = (String) estaciones.get(estaciones.size() - 1);
                    break;
                default:
                    throw new AssertionError();
            }

        } catch (SQLException e) {
            System.out.println("Ingresa otra cosa carajoooooo");
            e.printStackTrace();
        }
        return direccion;
    }

    //RUTA DE PUNTO A TO PUNTO B------------------------------------------------------------------------------
    public void mismaLinea(String idEstA, String idEstB, String idLin) {
        Conexion conexion = new Conexion();
        int direccion = 0;
        ArrayList estaciones = new ArrayList();
        boolean salir = true;
        try (Connection con = conexion.estableceConexion()) {
            if (con == null) {
                System.out.println("No se pudo establecer la conexión.");
                return;
            }

            ArrayList mismaLinea = new ArrayList<>();
            System.out.println("Estan en la misma linea");
            int nodoA = Integer.parseInt(idEstA);
            int nodoB = Integer.parseInt(idEstB);

            if (nodoA > nodoB)/*Desceiende*/ {
                do {
                    String estacionAgregada = String.valueOf(nodoA);
                    String query2 = "SELECT * FROM registro WHERE idL = ? AND idE = ?";
                    try (PreparedStatement estacion = con.prepareStatement(query2)) {
                        estacion.setString(1, idLin);
                        estacion.setString(2, estacionAgregada);
                        ResultSet rsA = estacion.executeQuery();

                        if (rsA.next()) { // Verifica si hay resultados
                            String nombreA = rsA.getString("nombre");
                            mismaLinea.add(nombreA);
                        } else {
                            System.out.println("Error, algo salio mal");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    nodoA--;
                } while (nodoA != nodoB - 1);

                //2 min iniciales
                //1 min de estacion a estacion
                //3 min de transbordo
                int min = 3;
                System.out.println("Suba en la estacion " + mismaLinea.get(0) + " con direccion a " + direccionA(idLin, 1));
                System.out.println("RUTA:");
                System.out.print("Inicio: ->" + mismaLinea.get(0) + " -> ");
                for (int i = 1; i < mismaLinea.size() - 1; i++) {
                    System.out.print(mismaLinea.get(i) + " -> ");
                    min += 1;
                }
                System.out.print("Destino: ->" + mismaLinea.get(mismaLinea.size() - 1));
                System.out.println("\nTimepo de viaje total: " + min + " minutos");

            } else/*Asciende*/ {
                do {
                    String estacionAgregada = String.valueOf(nodoA);
                    String query2 = "SELECT * FROM registro WHERE idL = ? AND idE = ?";
                    try (PreparedStatement estacion = con.prepareStatement(query2)) {
                        estacion.setString(1, idLin);
                        estacion.setString(2, estacionAgregada);
                        ResultSet rsA = estacion.executeQuery();

                        if (rsA.next()) { // Verifica si hay resultados
                            String nombreA = rsA.getString("nombre");
                            mismaLinea.add(nombreA);
                        } else {
                            System.out.println("Error, algo salio mal");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    nodoA++;
                } while (nodoA != nodoB + 1);

                int min = 3;
                System.out.println("Suba en la estacion " + mismaLinea.get(0) + " con direccion a " + direccionA(idLin, 1));
                System.out.println("RUTA:");
                System.out.print("INICIO: ->" + mismaLinea.get(0) + " -> ");
                for (int i = 1; i < mismaLinea.size() - 1; i++) {
                    System.out.print(mismaLinea.get(i) + " -> ");
                    min += 1;
                }
                System.out.print("DESTINO: ->" + mismaLinea.get(mismaLinea.size() - 1));
                System.out.println("\nTimepo de viaje total: " + min + " minutos");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //NODO MAS CERCANO
    public String nodoCercano(String idEstA, String idLin) {
        String idLinA = null;
        String idEst = null;
        String nombre = null;
        String tipo = null;

        Conexion conexion = new Conexion();
        int nodoA = Integer.parseInt(idEstA);
        int originalNodoA = nodoA;

        try (Connection con = conexion.estableceConexion()) {
            if (con == null) {
                System.out.println("No se pudo establecer la conexión.");
                return null;
            }

            boolean encontrado = false;
            String estacionAgregada;
            String query2 = "SELECT * FROM registro WHERE idL = ? AND idE = ?";

            // Decrementar para buscar el nodo tipo 1
            while (nodoA >= 0) {
                estacionAgregada = String.valueOf(nodoA);
                try (PreparedStatement estacion = con.prepareStatement(query2)) {
                    estacion.setString(1, idLin);
                    estacion.setString(2, estacionAgregada);
                    ResultSet rsA = estacion.executeQuery();

                    if (rsA.next()) {
                        if (rsA.getString("tipo").equals("1")) {
                            nombre = rsA.getString("nombre");
                            return nombre;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                nodoA--;
            }

            // Si no se encuentra decrementando, reiniciar nodoA y comenzar a incrementar
            nodoA = originalNodoA + 1;
            while (true) {
                estacionAgregada = String.valueOf(nodoA);
                try (PreparedStatement estacion = con.prepareStatement(query2)) {
                    estacion.setString(1, idLin);
                    estacion.setString(2, estacionAgregada);
                    ResultSet rsA = estacion.executeQuery();

                    if (rsA.next()) {
                        if (rsA.getString("tipo").equals("1")) {
                            nombre = rsA.getString("nombre");
                            return nombre;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                nodoA++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Devolver null si no se encuentra un nodo tipo 1
    }

}
