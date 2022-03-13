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
public class Plainte {

    public Plainte(
         int id,
     String date,
     String telephone,
     String email,
     String province,
     String id_tiquet,
     String message,
     String statut,
     String piecejointe_id,
     String refference
    ){
        this.id = id;
        this.date = date;
        this.telephone = telephone;
        this.email = email;
        this.province = province;
        this.id_tiquet = id_tiquet;
        this.message = message;
        this.piecejointe_id = piecejointe_id;
        this.statut = statut;
        this.refference = refference;
    }
    
    public int id;
    public String date;
    public String telephone;
    public String email;
    public String province;
    public String id_tiquet;
    public String message;
    public String piecejointe_id;
    public String statut;
    public String refference;
    
}
