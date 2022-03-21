package com.epst.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.xml.bind.annotation.XmlRootElement;


@Accessors(chain=true)
//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
@ToString
@XmlRootElement
public class Magasin {

    public Magasin(int id, String libelle, String descirption, byte[] piecejointe, String types, String date){
        this.date = date; this.description = descirption; this.id = id; this.libelle = libelle; this.piecejointe = piecejointe;
        this.types = types;
    }

    public int id;

    public String libelle;

    public String description;

    public String date;

    public byte[] piecejointe;

    public String types;


}
