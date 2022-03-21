package com.epst.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
            //con = DriverManager.getConnection("heroku pg:psqllocalhost:5432/agent_epst", "postgres", "joellungu");
            //Statement stmt = con.createStatement();
            //
            //stmt.executeUpdate(sql);
            //
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
}
