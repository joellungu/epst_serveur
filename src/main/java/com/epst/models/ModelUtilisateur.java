package com.epst.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.epst.beans.Utilisateur;

public class ModelUtilisateur {

    SeConnecter seConnecter = new SeConnecter();
    Connection con;
    //
    ResultSet résultats = null;
    //

    public ModelUtilisateur(){
        try{
            con = seConnecter.con;
        }catch(Exception ex){
            System.out.println("Erreur du à: "+ex.getMessage());
        }
    }
    //
    public Utilisateur getUtilisateur(int id){
        Utilisateur utilisateur = new Utilisateur();
        String requete = "SELECT * FROM agent_epst where id = "+id;
        //
        try {
            Statement stmt = con.createStatement();
            résultats = stmt.executeQuery(requete);
            //
            boolean encore = résultats.next();

            while (encore) {
                utilisateur = new Utilisateur(
                    résultats.getInt("id"),
                    résultats.getString(2),
                    résultats.getString(3),
                    résultats.getString(4),
                    résultats.getString(5),
                    résultats.getString(6),
                    résultats.getString(7),
                    résultats.getString(8),
                    résultats.getInt(9),
                    résultats.getString(10),
                    résultats.getString(11)
                );
                //
                System.out.println();
                encore = résultats.next();
            }
            //
        } catch (SQLException e) {
            //traitement de l'exception
        };
        return utilisateur;
    }
    //
    public List<Utilisateur> getAllUtilisateur(){
        List<Utilisateur> liste = new LinkedList<>();
        String requete = "SELECT * FROM agent_epst";

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
                    new Utilisateur(
                        résultats.getInt(1),
                        résultats.getString(2),
                        résultats.getString(3),
                        résultats.getString(4),
                        résultats.getString(5),
                        résultats.getString(6),
                        résultats.getString(7),
                        résultats.getString(8),
                        résultats.getInt(9),
                        résultats.getString(10),
                        résultats.getString(11)
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
            System.out.print(e.getId() + " ___ " + e.getEmail() + 
                "______" + e.getNom() + "____" + e.getRole() + "__");
        });

        return liste;
    }

    public String saveUtilisateur(Utilisateur utilisateur){
        String t = "";

        //
        try{
            String sql = "INSERT INTO agent_epst (id, adresse, date_de_naissance, email, nom, numero, postnom, prenom, role, matricule, id_statut) "+
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
 
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setLong(1, getId());
            statement.setString(2, utilisateur.getAdresse());
            statement.setString(3, utilisateur.getDate_de_naissance());
            statement.setString(4, utilisateur.getEmail());
            statement.setString(5, utilisateur.getNom());
            statement.setString(6, utilisateur.getNumero());
            statement.setString(7, utilisateur.getPostnom());
            statement.setString(8, utilisateur.getPrenom());
            statement.setInt(9, utilisateur.getRole());
            statement.setString(10, utilisateur.getMatricule());
            statement.setString(11, utilisateur.getId_statut());
            
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
            t = "A new user was inserted successfully!";
        }catch(Exception ex){
            System.out.println("erreur du à: "+ex);
            System.out.println("erreur du à: "+ex.getMessage());
            System.out.println("erreur du à: "+ex.getLocalizedMessage());
            System.out.println("erreur du à: "+ex.getCause());
            System.out.println("erreur du à: "+ex.getStackTrace());
            t = ""+ex+"\n"+ex.getMessage()+"\n"+ex.getLocalizedMessage()+"\n"+ex.getCause()+"\n"+ex.getStackTrace();
        }

        return t;
    }

    public int supprimerUtilisateur(int id){
        int t = 0;
        System.out.println("______________________________: "+id);
        //
        try{
            String sql = "DELETE FROM agent_epst WHERE id = ?";
 
            PreparedStatement statement = con.prepareStatement(sql);
            //statement.setInt(1, utilisateur.getId());
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
    
    public int miseaJourUtilisateur(Utilisateur utilisateur){
        int t = 0;
        System.out.print("****id: "+"_ ad:"+":\n__:\n"+
        utilisateur.getId()+":__:id\n"+
        utilisateur.getAdresse()+":__:adresse\n"+
        utilisateur.getEmail()+":__:email\n"+
        utilisateur.getNom()+":__:nom\n"+
        utilisateur.getNumero()+":__:numero\n"+
        utilisateur.getPostnom()+":__:postnom\n"+
        utilisateur.getPrenom()+":__:prenom\n"+
        utilisateur.getRole()+":__:role\n"+
        utilisateur.getMatricule()+":__:matricule\n"+
        utilisateur.getId_statut()+":__:id_statut\n"
                );
        try{
            String sql = "UPDATE agent_epst SET adresse = ?, date_de_naissance = ?, email = ?, nom = ?, numero = ?, postnom = ?, prenom = ?, role = ?, matricule = ?, id_statut = ?  WHERE id = ?";
            //
            PreparedStatement statement = con.prepareStatement(sql);

            
            //
            statement.setString(1, utilisateur.getAdresse());
            statement.setString(2, utilisateur.getDate_de_naissance());
            statement.setString(3, utilisateur.getEmail());
            statement.setString(4, utilisateur.getNom());
            statement.setString(5, utilisateur.getNumero());
            statement.setString(6, utilisateur.getPostnom());
            statement.setString(7, utilisateur.getPrenom());
            statement.setInt(8, utilisateur.getRole());
            statement.setString(9, utilisateur.getMatricule());
            statement.setString(10, utilisateur.getId_statut());
            //
            statement.setInt(11, utilisateur.getId());

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
//