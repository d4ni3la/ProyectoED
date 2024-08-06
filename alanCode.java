package com.mycompany.proyectoed2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
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

            //Seleccion de funciones-----------------------------
            //SELECCION DE ALGORITOMO--------------------------------------
            if (idLinA.equals(idLinB)) {
                mismaLinea(idEstA, idEstB, idLinA, true);
            } else {
                //No pertenecen a la mima linea
                if (nodoCercano(idEstA, idLinA).equals(nodoCercano(idEstB, idLinB))) {
                    viajeEnL(idEstA, idLinA, idEstB, idLinB);

                }
            }
            //Ultimo catch---------------------------------
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //OBTIENE LA DIRECCION A DONDE DEBE DE IR LA PERSONA
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
    public ArrayList mismaLinea(String idEstA, String idEstB, String idLin, boolean x) {
        Conexion conexion = new Conexion();
        ArrayList mismaLinea = new ArrayList<>();
        int direccion = 0;
        ArrayList estaciones = new ArrayList();
        boolean salir = true;
        try (Connection con = conexion.estableceConexion()) {
            if (con == null) {
                System.out.println("No se pudo establecer la conexión.");
            }
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

                if (x) {
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

                }
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

                if (x) {
                    int min = 3;
                    System.out.println("Suba en la estacion " + mismaLinea.get(0) + " con direccion a " + direccionA(idLin, 2));
                    System.out.println("RUTA:");
                    System.out.print("Inicio: ->" + mismaLinea.get(0) + " -> ");
                    for (int i = 1; i < mismaLinea.size() - 1; i++) {
                        System.out.print(mismaLinea.get(i) + " -> ");
                        min += 1;
                    }
                    System.out.print("Destino: ->" + mismaLinea.get(mismaLinea.size() - 1));
                    System.out.println("\nTimepo de viaje total: " + min + " minutos");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mismaLinea;
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

    //ALGOTRITMO PARA VIAJES CON EL MISMO NODO DE CRUCE MAS CERCANO PERO EN DIFERENTES LINEAS
    public void viajeEnL(String idEstA, String idLinA, String idEstB, String idLinB) {
        Conexion conexion = new Conexion();
        try (Connection con = conexion.estableceConexion()) {
            if (con == null) {
                System.out.println("No se pudo establecer la conexión.");
                return;
            }

            String noEstacionA = null;
            String noEstacionB = null;

            String queryCruce = "SELECT * FROM registro WHERE nombre = ? and idL = ?";
            // Busca el nodo mas cercano y retorna la estacion de la linea en la que esta
            //Linea A
            try (PreparedStatement stmtCercano = con.prepareStatement(queryCruce)) {
                stmtCercano.setString(1, nodoCercano(idEstA, idLinA));
                stmtCercano.setString(2, idLinA);
                ResultSet rsCercano = stmtCercano.executeQuery();

                if (rsCercano.next()) {
                    noEstacionA = rsCercano.getString("idE");

                } else {
                    System.out.println("Error, algo salio mal");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //Linea B
            try (PreparedStatement stmtCercano = con.prepareStatement(queryCruce)) {
                stmtCercano.setString(1, nodoCercano(idEstB, idLinB));
                stmtCercano.setString(2, idLinB);
                ResultSet rsCercano = stmtCercano.executeQuery();

                if (rsCercano.next()) {
                    noEstacionB = rsCercano.getString("idE");
                } else {
                    System.out.println("Error, algo salio mal");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            ArrayList estaciones = new ArrayList();

            //Para A
            for (int i = 0; i < mismaLinea(idEstA, noEstacionA, idLinA, false).size(); i++) {
                estaciones.add(mismaLinea(idEstA, noEstacionA, idLinA, false).get(i));
            }
            //Para B
            for (int i = 0; i < mismaLinea(noEstacionB, idEstB, idLinB, false).size(); i++) {
                estaciones.add(mismaLinea(noEstacionB, idEstB, idLinB, false).get(i));
            }

            int nodoA = Integer.parseInt(idEstA);
            int nodoA2 = Integer.parseInt(noEstacionA);
            int nodoB = Integer.parseInt(idEstB);
            int nodoB2 = Integer.parseInt(noEstacionB);

            int min = 2;
            boolean entrar = false;
            if (nodoA > nodoA2) {

                System.out.println("Suba en la estacion " + estaciones.get(0) + " con direccion a " + direccionA(idLinA, 1));
                System.out.println("RUTA:");
                System.out.print("Inicio: ->" + estaciones.get(0) + " -> ");
                for (int i = 1; i < estaciones.size() - 1; i++) {
                    if (estaciones.get(i).equals(nodoCercano(idEstA, idLinA))) {
                        if (nodoB > nodoB2 && entrar) {
                            System.out.println(" Transborde en " + nodoCercano(idEstA, idLinA) + " a la linea " + idLinB + " con direccion a " + direccionA(idLinB, 2));
                            min += 3;
                        } else if (nodoB < nodoB2 && entrar) {
                            System.out.print(" Transborde en " + nodoCercano(idEstA, idLinA) + " a la linea " + idLinB + " con direccion a " + direccionA(idLinB, 1));
                            min += 3;
                        }
                        entrar = true;
                    }
                    System.out.print(estaciones.get(i) + " -> ");
                    min += 1;
                }
                System.out.print("Destino: ->" + estaciones.get(estaciones.size() - 1));
                System.out.println("\nTiempo de viaje total " + min + " minutos");
            } else {
                System.out.println("Suba en la estacion " + estaciones.get(0) + " con direccion a " + direccionA(idLinA, 2));
                System.out.println("RUTA:");
                System.out.print("Inicio: ->" + estaciones.get(0) + " -> ");
                for (int i = 1; i < estaciones.size() - 1; i++) {
                    if (estaciones.get(i).equals(nodoCercano(idEstA, idLinA))) {
                        if (nodoB > nodoB2 && entrar) {
                            System.out.println("Transborde en " + nodoCercano(idEstA, idLinA) + " a la linea " + idLinB + " con direccion a " + direccionA(idLinB, 2));
                            min += 3;
                        } else if (nodoB < nodoB2 && entrar) {
                            System.out.print("Transborde en " + nodoCercano(idEstA, idLinA) + " a la linea " + idLinB + " con direccion a " + direccionA(idLinB, 1));
                            min += 3;
                        }
                        entrar = true;
                    }
                    System.out.print(estaciones.get(i) + " -> ");
                    min += 1;
                }
                System.out.print("Destino: ->" + estaciones.get(estaciones.size() - 1));
                System.out.println("\nTiempo de viaje total " + min + " minutos");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //MATIZ 
    public void floyd(int inicio ,int destino) {
        ArrayList<Integer> listaNodos = new ArrayList<>();
        ArrayList<Integer> listaNodos2 = new ArrayList<>();
        ArrayList<Integer> nombresNodos = new ArrayList<>();
        HashSet<Integer> listado = new HashSet<>();

        int k, i, j, n;
        int matM[][] = {
            {0, 1, 3, 99, 99, 4, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {1, 0, 99, 99, 99, 99, 3, 99, 6, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {3, 99, 0, 2, 99, 2, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 2, 0, 5, 99, 99, 3, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 5, 0, 99, 99, 99, 99, 99, 99, 6, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {4, 99, 2, 99, 99, 0, 3, 2, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 3, 99, 99, 99, 3, 0, 2, 99, 2, 99, 99, 7, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 3, 99, 2, 2, 0, 99, 3, 2, 99, 99, 99, 99, 3, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 6, 99, 99, 99, 99, 99, 99, 0, 99, 99, 99, 4, 99, 99, 99, 99, 99, 4, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 2, 3, 99, 0, 2, 99, 99, 5, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 2, 99, 2, 0, 3, 99, 99, 2, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 6, 99, 99, 99, 99, 99, 3, 0, 99, 99, 99, 3, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 7, 99, 4, 99, 99, 99, 0, 4, 99, 99, 99, 99, 99, 3, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 5, 99, 99, 4, 0, 3, 99, 99, 99, 99, 99, 3, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 2, 99, 99, 3, 0, 2, 2, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 3, 99, 99, 99, 3, 99, 99, 2, 0, 99, 3, 99, 99, 99, 3, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 2, 99, 0, 2, 99, 99, 4, 99, 2, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 3, 2, 0, 99, 99, 99, 2, 99, 4, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 4, 99, 99, 99, 99, 99, 99, 99, 99, 99, 0, 5, 99, 99, 99, 99, 99, 99, 11, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 3, 99, 99, 99, 99, 99, 5, 0, 3, 99, 99, 99, 2, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 3, 99, 99, 4, 99, 99, 3, 0, 99, 99, 99, 3, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 3, 99, 2, 99, 99, 99, 0, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 2, 99, 99, 99, 99, 99, 0, 3, 3, 99, 7, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 4, 99, 99, 99, 99, 3, 0, 99, 6, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 2, 3, 99, 3, 99, 0, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 6, 99, 0, 99, 2},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 11, 99, 99, 99, 7, 99, 99, 99, 0, 4},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 2, 4, 0}
        };

        int matT[][] = {
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99},
            {99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99}
        };

        n = matM.length;
        for (k = 0; k < n; k++) //Algoritmo de Floyd
        {
            for (i = 0; i < n; i++) {
                for (j = 0; j < n; j++) {
                    if (matM[i][k] + matM[k][j] < matM[i][j]) {
                        matM[i][j] = matM[i][k] + matM[k][j];
                        matT[i][j] = k;
                    }
                }
            }
        }
        System.out.println("MATRIZ M");
        System.out.println("\t[0]\t[1]\t[2]\t[3]\t[4]\t[5]\t[6]\t[7]\t[8]\t[9]\t[10]\t[11]\t[12]\t[13]\t[14]\t[15]\t[16]\t[17]\t[18]\t[19]\t[20]\t[21]\t[22]\t[23]\t[24]\t[25]\t[26]\t[27]");
        for (i = 0; i < n; i++) {
            System.out.print("[" + i + "]\t");
            for (j = 0; j < n; j++) {
                System.out.print(matM[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("\n\n");
        System.out.println("MATRIZ T");
        System.out.println("\t[0]\t[1]\t[2]\t[3]\t[4]\t[5]\t[6]\t[7]\t[8]\t[9]\t[10]\t[11]\t[12]\t[13]\t[14]\t[15]\t[16]\t[17]\t[18]\t[19]\t[20]\t[21]\t[22]\t[23]\t[24]\t[25]\t[26]\t[27]");
        for (i = 0; i < n; i++) {
            System.out.print("[" + i + "]\t");
            for (j = 0; j < n; j++) {
                System.out.print(matT[i][j] + "\t");
            }
            System.out.println();
        }

        // ---------------- VALIDACIONES
        

        // nodos por donde debe pasar
        listaNodos = listadoNodos(matT, inicio, destino);

        System.out.println("Nodos de cruce desde " + inicio + " hasta " + destino + ": " + listaNodos);
        int tam = listaNodos.size();

        for (int l = 0; l < tam; l++) {
            int num = listaNodos.get(l);
            listado.add(num);
        }
        System.out.println("Nodos sin repetir: " + listado);

        for (int l = 0; l < tam; l++) {
            if (l == 0) {
                int numero = listaNodos.get(l);
                listaNodos2.add(numero);
            } else if (l > 0) {
                if (listaNodos.get(l) == listaNodos.get(l - 1)) {
                    int numero = listaNodos.get(l);
                    listaNodos2.add(numero);
                }
            }

        }
        System.out.println("Nodos" + listaNodos2);
    }

    // agrega conforme al camino mas corto
    private static ArrayList<Integer> listadoNodos(int[][] matT, int inicio, int destino) {
        ArrayList<Integer> camino = new ArrayList<>();
        obtenerCamino(matT, inicio, destino, camino);
        return camino;
    }

    private static void obtenerCamino(int[][] matT, int inicio, int destino, ArrayList<Integer> camino) {
        if (matT[inicio][destino] == 99) {
            // si es 99 es DIRECTO
            camino.add(destino);
        } else {
            // obtener el nodo
            int k = matT[inicio][destino];
            obtenerCamino(matT, inicio, k, camino);
            // Agregar el nodo destino intermedio
            camino.add(k);
            obtenerCamino(matT, k, destino, camino);
        }
    }
}
