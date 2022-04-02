package org.epst.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Accessors(chain=true)
//@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
@ToString
public class Magasin {

    public Magasin(Long id, String libelle, String descirption, byte[] piecejointe, String types, String date, String extention){
        this.date = date; this.description = descirption; this.id = id; this.libelle = libelle; this.piecejointe = piecejointe;
        this.types = types; this.extention = extention;
    }

    public Long id;

    public String libelle;

    public String description;

    public String date;

    public byte[] piecejointe;

    public String types;

    public String extention;


}
