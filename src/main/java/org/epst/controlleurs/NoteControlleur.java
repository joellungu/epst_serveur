package org.epst.controlleurs;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.epst.beans.NoteTraitementBean;
import org.epst.models.ModelNoteTraitement;

@Path("/note")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NoteControlleur {

    private static final ObjectMapper mapper = new ObjectMapper();


    //@Transactional
    @Path("/ajoule")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response noterMa(NoteTraitementBean noteTraitementBean){
        System.out.println("Le id: "+noteTraitementBean.getId());
        System.out.println("Le nom: "+noteTraitementBean.getNomIdmin());
        System.out.println("Le ref: "+noteTraitementBean.getReference());
        System.out.println("Le note: "+noteTraitementBean.getNote());
        //
        ModelNoteTraitement modelNoteTraitement = new ModelNoteTraitement();
        Long v = modelNoteTraitement.saveNote(noteTraitementBean);
        ObjectNode json = mapper.createObjectNode();
        json.put("status", v);
        return Response.status(Response.Status.CREATED).entity("ok").build();
    }

}
