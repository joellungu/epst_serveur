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
public class Piecejointe {

    public Piecejointe(
        Long id,
        Long piecejointe_id,
        byte[] donne,
        String type
    ){
        this.id = id;
        this.piecejointe_id = piecejointe_id;
        this.donne = donne;
        this.type = type;
    }

    public Long id;
    public Long piecejointe_id;
    public byte[] donne;
    public String type;
    
}
