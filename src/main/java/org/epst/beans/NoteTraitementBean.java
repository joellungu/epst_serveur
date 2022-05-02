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
public class NoteTraitementBean {
    public Long id;
    public String nomIdmin;
    public String reference;
    public String note;

    public NoteTraitementBean(Long id, String nomIdmin, String reference, String note){
        this.id = id; this.nomIdmin = nomIdmin; this.note = note; this.reference = reference;
    }
}
