package com.epst.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.epst.beans.Magasin;
import com.epst.heroku.Main;

public class ModelMagasin {
    
    //SeConnecter seConnecter = new SeConnecter();
    Connection con;
    //
    ResultSet résultats = null;
    //
    Magasin magasin;

    public ModelMagasin(){
        try{
            con = Main.con.con;
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
    //

    public Magasin getMagasin(int id){
        Magasin Magasin = new Magasin();
        String requete = "SELECT id, libelle, description, piecejointe, types, date_mise_en_ligne FROM magasin where id = "+id;
        //
        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(requete);
            //
            boolean encore = résultats.next();

            while (encore) {
                magasin = new Magasin(
                    résultats.getInt("id"),
                    résultats.getString(2),
                    résultats.getString(3),
                    résultats.getBytes(4),
                    résultats.getString(5),
                    résultats.getString(6)
                );
                //
                System.out.println();
                encore = résultats.next();
            }
            //
        } catch (SQLException e) {
            //traitement de l'exception
        };
        return magasin;
    }
    //
    public List<Magasin> getAllMagasin(String type){
        List<Magasin> liste = new LinkedList<>();
        System.out.println("le type vaut: "+type);
        String requete = "SELECT id, libelle, description, types, date_mise_en_ligne FROM magasin where types = '"+type+"'";

        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(requete);
            //
            boolean encore = résultats.next();

            while (encore) {
                /*
                System.out.print("****id: "+résultats.getInt(1)+"_ ad:"+":\n__:\n"+
                résultats.getInt(1)+":__:id\n"+
                résultats.getString(2)+":__:adresse\n"+
                résultats.getString(3)+":__:email\n"+
                résultats.getString(4)+":__:nom\n"+
                résultats.getString(5)+":__:numero\n"+
                résultats.getString(6)+":__:postnom\n"
                );
                */
                liste.add(
                    new Magasin(
                        résultats.getInt(1),
                        résultats.getString(2),
                        résultats.getString(3),
                        null,
                        résultats.getString(4),
                        résultats.getString(5)
                    )
                );

                System.out.println("La langueur: "+liste.size());
                encore = résultats.next();
            }
            //
        } catch (SQLException e) {
            //traitement de l'exception
            System.out.println(e);
        };
        //
        liste.forEach((e)->{
            System.out.print(e.getId() + " ___ " + e.getDate() + 
                "______" + e.getDescription() + "____" + e.getLibelle() + "__");
        });

        return liste;
    }

    public int saveMagasin(Magasin Magasin){
        int t = 0;

        //
        try{
            String sql = "INSERT INTO magasin (id, libelle, description, piecejointe, date_mise_en_ligne, types) "+
            "VALUES (?, ?, ?, ?, ?, ?)";
    
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, getId());
            statement.setString(2, Magasin.getLibelle());
            statement.setString(3, Magasin.getDescription());
            statement.setBytes(4, Magasin.getPiecejointe());
            statement.setString(5, Magasin.getDate());
            statement.setString(6, Magasin.getTypes());
            
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            t = rowsInserted;
        }catch(Exception ex){
            System.out.println("erreur du à: "+ex);
            System.out.println("erreur du à: "+ex.getMessage());
            System.out.println("erreur du à: "+ex.getLocalizedMessage());
            System.out.println("erreur du à: "+ex.getCause());
            System.out.println("erreur du à: "+ex.getStackTrace());
            t = 0;
        }

        return t;
    }

    public int supprimerMagasin(int id){
        int t = 0;
        System.out.println("______________________________: "+id);
        //
        try{
            String sql = "DELETE FROM magasin WHERE id = ?";
    
            PreparedStatement statement = con.prepareStatement(sql);
            //statement.setInt(1, Magasin.getId());
            statement.setLong(1, id);
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("____________delete successfully!");
            }
            t = rowsInserted;
        }catch(Exception ex){
            System.out.println("______________erreur du à: "+ex.getMessage());
            t = 0;
        }

        return t;
    }
    
    public int miseaJourMagasin(Magasin Magasin){
        int t = 0;
        System.out.print("****id: "+"_ ad:"+":\n__:\n"+
        Magasin.getId()+":__:id\n"+
        Magasin.getDate()+":__:adresse\n"+
        Magasin.getDescription()+":__:email\n"+
        Magasin.getLibelle()+":__:nom\n"
                );
        try{
            String sql = "UPDATE magasin SET libelle = ?, description = ?, piecejointe = ?, date = ?, types = ? WHERE id = ?";
            //
            PreparedStatement statement = con.prepareStatement(sql);

            
            //
            statement.setString(1, Magasin.getLibelle());
            statement.setString(2, Magasin.getDescription());
            statement.setBytes(3, Magasin.getPiecejointe());
            statement.setString(4, Magasin.getDate());
            statement.setString(5, Magasin.getTypes());
            //
            statement.setInt(6, Magasin.getId());

            t = statement.executeUpdate();

        }catch(Exception ex){
            System.out.println("erreur du à: "+ex.getMessage());
            t = 0;
        }
        return t;
    }

    private int getId(){
        int t = 0;
        //
        Random r = new Random();
        //
        t = Integer.parseInt("1"+r.nextInt(11)+""+r.nextInt(11)+""+r.nextInt(11)+""+r.nextInt(11)+""+r.nextInt(11)+""+r.nextInt(11)+r.nextInt(11)+"");
        return t;
    }

}
