package org.epst.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


//@Entity
@Accessors(chain=true)
//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
@ToString
public class Magasin {//PanacheEntityBase

    public Magasin(Long id, String libelle, String descirption, byte[] piecejointe, String types, String date, String extention){
        this.date = date; this.description = descirption; this.id = id; this.libelle = libelle; this.piecejointe = piecejointe;
        this.types = types; this.extention = extention;
    }

    /*
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequence")
    */

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    public String libelle;

    public String description;

    public String date;

    public byte[] piecejointe;

    public String types;

    public String extention;


}
