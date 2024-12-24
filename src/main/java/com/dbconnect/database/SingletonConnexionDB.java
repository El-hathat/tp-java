package com.dbconnect.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDB {


    private SingletonConnexionDB() {}

    public static Connection getConnexion() {

            try {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_equipes", "root", "");
            }
            catch(SQLException e){
                e.printStackTrace();
                return null;
            }


    }
}

