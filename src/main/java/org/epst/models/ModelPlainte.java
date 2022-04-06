package org.epst.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.epst.beans.Piecejointe;
import org.epst.beans.Plainte;

public class ModelPlainte {
    
    SeConnecter seConnecter = new SeConnecter();
    Connection con;
    //
    ResultSet résultats = null;
    //
    Plainte plainte;

    public ModelPlainte(){
        try{
            con = seConnecter.con;
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
    //

    public Plainte getPlainte(int id){
        Plainte Plainte = new Plainte();
        String requete = "SELECT * FROM depot_plainte where id = "+id;
        //
        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(requete);
            //
            boolean encore = résultats.next();
            /*
            int id,
            String date,
            String telephone,
            String email,
            String province,
            String id_tiquet,
            String message,
            String piecejointe_id,
            String statut
            */

            while (encore) {
                Plainte = new Plainte(
                    résultats.getLong(1),
                    résultats.getString(2),
                    résultats.getString(3),
                    résultats.getString(4),
                    résultats.getString(5),
                    résultats.getString(6),
                    résultats.getString(7),
                    résultats.getString(8),
                    résultats.getString(9),
                    résultats.getString(10),
                    résultats.getString(11),
                    résultats.getString(12)
                );
                //
                System.out.println();
                encore = résultats.next();
            }
            //
        } catch (SQLException e) {
            //traitement de l'exception
        };
        return Plainte;
    }
    //
    public List<Plainte> getAllPlainte(int statut){
        List<Plainte> liste = new LinkedList<>();
        System.out.println("le type vaut: "+statut);
        String requete = "SELECT * FROM depot_plainte WHERE id_statut = '"+statut+"'";
        //statut == "0" ? "SELECT * FROM plainte" : "SELECT * FROM plainte where statut = "+statut;

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
                résultats.getString(6)+":__:postnom\n"+
                résultats.getString(7)+":__:prenom\n"+
                résultats.getString(8)+":__:role\n"+
                résultats.getString(10)+":__:matricule\n"+
                résultats.getString(11)+":__:id_statut\n"
                );
                */
                liste.add(
                    new Plainte(
                        résultats.getLong(1),
                        résultats.getString(2),
                        résultats.getString(3),
                        résultats.getString(4),
                        résultats.getString(5),
                        résultats.getString(6),
                        résultats.getString(7),
                        résultats.getString(8),
                        résultats.getString(9),
                        résultats.getString(10),
                        résultats.getString(11),
                        résultats.getString(12)
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
                "______" + e.getEmail() + "____" + e.getTelephone() + "__");
        });

        return liste;
    }
    //
    //
    public List<Piecejointe> getAllPiecejointe(int piecejointe_id){
        List<Piecejointe> liste = new LinkedList<>();
        System.out.println("le type vaut: "+piecejointe_id);
        String requete = "SELECT * FROM piecejointe where piecejointe_id = '"+piecejointe_id+"'";

        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(requete);
            //
            boolean encore = résultats.next();

            while (encore) {
                liste.add(
                    new Piecejointe(
                        résultats.getInt(1),
                        résultats.getString(2),
                        résultats.getBytes(3),
                        résultats.getString(4)
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
            System.out.print(e.getId() + " ___ " + e.getPiecejointe_id() + 
                "______" + e.getId() + "____" + e.getPiecejointe_id() + "__");
        });

        return liste;
    }

    public Long savePlainte(Plainte plainte){
        //int t = 0;

        Long piecejointe_id = 0L;

        //
        try{
            piecejointe_id = getId();
            //
            String sql = "INSERT INTO depot_plainte (id, envoyeur, telephone, email, destinateur, id_tiquet, message, id_statut, piecejointe_id, reference, date, province) "+
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, getId());
            statement.setString(2, plainte.getEnvoyeur()); 
            statement.setString(3, plainte.getTelephone());
            statement.setString(4, plainte.getEmail());
            statement.setString(5, plainte.getDestinateur());
            statement.setString(6, plainte.getId_tiquet());
            statement.setString(7, plainte.getMessage());
            statement.setString(8, plainte.getId_statut());
            statement.setString(9, ""+piecejointe_id);
            statement.setString(10, plainte.getReference());
            statement.setString(11, plainte.getDate());
            statement.setString(12, plainte.getProvince());
            //
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            //t = rowsInserted;
        }catch(Exception ex){
            System.out.println("erreur du à: "+ex);
            System.out.println("erreur du à: "+ex.getMessage());
            System.out.println("erreur du à: "+ex.getLocalizedMessage());
            System.out.println("erreur du à: "+ex.getCause());
            System.out.println("erreur du à: "+ex.getStackTrace());
            //t = 0;
            piecejointe_id = 0l;
        }

        return piecejointe_id;
    }

    public int savePiecejointe(Long piecejointe_id, String type, byte[] piecejointe){
        int t = 0;

        //int piecejointe_id = 0;

        //
        try{
            //piecejointe_id = getId();
            //
            String sql = "INSERT INTO piecejointe (id, piecejointe_id, donne, type) "+
            "VALUES (?, ?, ?, ?)";
    
            PreparedStatement statement = con.prepareStatement(sql);
            //statement.setInt(1, piecejointe.getId());
            statement.setLong(1, getId()); 
            statement.setLong(2, piecejointe_id);
            statement.setBytes(3, piecejointe);
            statement.setString(4, type);
            
            
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

    public int miseaJourPlainte(Plainte Plainte){
        int t = 0;
        System.out.print("****id: "+"_ ad:"+":\n__:\n"+
        Plainte.getId()+":__:id\n"+
        Plainte.getDate()+":__:date\n"+
        Plainte.getEmail()+":__:email\n"+
        Plainte.getProvince()+":__:Province\n"
                );
        try{
            String sql = "UPDATE plainte SET envoyeur = ? telephone = ? email = ? destinateur = ? id_tiquet = ? message = ? id_statut = ? piecejointe_id = ? reference = ? date = ? province WHERE id = ?";
            //"UPDATE plainte SET date = ?, telephone = ?, email = ?, province = ?, id_tiquet = ?, message = ?, statut = ?, piecejointe_id = ?, reference = ? WHERE id = ?";
            //
            PreparedStatement statement = con.prepareStatement(sql);

            //
            //statement.setInt(1, plainte.getId());
            statement.setString(1, plainte.getEnvoyeur()); 
            statement.setString(2, plainte.getTelephone());
            statement.setString(3, plainte.getEmail());
            statement.setString(4, plainte.getDestinateur());
            statement.setString(5, plainte.getId_tiquet());
            statement.setString(6, plainte.getMessage());
            statement.setString(7, plainte.getId_statut());
            statement.setString(8, plainte.getPiecejointe_id());
            statement.setString(9, plainte.getReference());
            statement.setString(10, plainte.getDate());
            statement.setString(11, plainte.getProvince());
            //
            statement.setLong(12, plainte.getId());
            //
            t = statement.executeUpdate();

        }catch(Exception ex){
            System.out.println("erreur du à: "+ex.getMessage());
            t = 0;
        }
        return t;
    }

    private Long getId(){
        Random random = new Random();
        long random63BitLong = random.nextLong();
        return random63BitLong;
    }

}
