//
//  main.cpp
//  proyectoED
//
//  Created by Daniela Pérez on 02/07/24.
//

#include <iostream>
/*
#include <mysql_driver.h>
#include <mysql_connection.h>
#include <cppconn/statement.h>
#include <cppconn/resultset.h>
#include <cppconn/exception.h>*/

#include <mysql.h>

using namespace std;


void finish_with_error(MYSQL *con) {
    cerr << "Error: " << mysql_error(con) << endl;
    mysql_close(con);
    exit(1);
}

int main(int argc, const char * argv[]) {
    // insert code here...
    MYSQL *con = mysql_init(0);
    con= mysql_real_connect(con, "localhost", "root", "PSW.d4ni", "metro", 3306, NULL, 0);
    
    /*
        if (con == nullptr) {
            cerr << "mysql_init() failed\n";
            return EXIT_FAILURE;
        }

        // Conectar a la base de datos
        if (mysql_real_connect(con, "localhost", "root", "PSW.d4ni", "metro", 0, nullptr, 0) == nullptr) {
            finish_with_error(con);
        }
        
        
        // Crear una tabla
        if (mysql_query(con, "CREATE TABLE IF NOT EXISTS Prueba(Id INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(255))")) {
            finish_with_error(con);
        }
        // Insertar datos en la tabla
        if (mysql_query(con, "INSERT INTO Prueba(Nombre) VALUES('John Doe')")) {
            finish_with_error(con);
        }*/

        // Consultar datos de la tabla
        if (mysql_query(con, "SELECT * FROM registro")) {
            finish_with_error(con);
        }

        MYSQL_RES *result = mysql_store_result(con);

        if (result == nullptr) {
            finish_with_error(con);
        }

        int num_fields = mysql_num_fields(result);
        MYSQL_ROW row;

        while ((row = mysql_fetch_row(result))) {
            for (int i = 0; i < num_fields; i++) {
                cout << (row[i] ? row[i] : "NULL") << ' ';
            }
            cout << endl;
        }

        // Liberar el resultado y cerrar la conexión
        mysql_free_result(result);
        mysql_close(con);
}
