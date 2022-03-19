package com.epst.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class SeConnecter {

    public Connection con;
    //
    ResultSet résultats = null;
    //

    public SeConnecter(){
        try{
            String dbUrl = System.getenv("JDBC_DATABASE_URL");
            con = DriverManager.getConnection(dbUrl);
            //con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agent_epst", "postgres", "joellungu");
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
}
