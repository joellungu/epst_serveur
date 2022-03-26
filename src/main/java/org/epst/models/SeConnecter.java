package org.epst.models;

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
            //String dbUrl = System.getenv("JDBC_DATABASE_URL");
            //con = DriverManager.getConnection(dbUrl);
            //con = DriverManager.getConnection("heroku pg:psqllocalhost:5432/agent_epst", "postgres", "joellungu");
            con = DriverManager.getConnection("jdbc:postgresql://ec2-44-194-225-27.compute-1.amazonaws.com:5432/dctc0vddfjbpgp", 
            "gwrxvcqcbndkay", 
            "bc997dbd9f2412d458ce6bd8418085e72a6dd3d97b860ec2fc3c614acfc32bcb");
            //Statement stmt = con.createStatement();
            //
            //stmt.executeUpdate(sql);
            //
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
}
