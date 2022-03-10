package com.epst.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.xml.bind.annotation.XmlRootElement;


//@Accessors(chain=true)
//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
//@ToString
@XmlRootElement
public class Utilisateur {

    public Utilisateur(
             int id,
             String adresse,
             String date_de_naissance,
             String email,
            String nom,
            String numero,
            String postnom,
            String prenom,
            int role,
            String matricule,
            String id_statut
        ){
        this.adresse = adresse;
        this.date_de_naissance = date_de_naissance;
        this.id = id;
        this.email = email;
        this.id_statut = id_statut;
        this.matricule = matricule;
        this.nom = nom;
        this.numero = numero;
        this.postnom = postnom;
        this.role = role;
        this.prenom = prenom;
    }

    public int id;

    public String nom;

    public String postnom;

    public String prenom;

    public String date_de_naissance;

    public String numero;

    public String email;

    public String adresse;

    public int role;

    public String matricule;

    public String id_statut;
}
