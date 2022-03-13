package com.epst.controlleurs;

import java.util.List;

import com.epst.beans.Piecejointe;
import com.epst.beans.Plainte;
import com.epst.models.ModelPlainte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/piecejointe")
public class PiecejointeControlleur {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    ModelPlainte modelPlainte = new ModelPlainte();
    
    @Path("/all/{piecejointe_id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Piecejointe> getAllPiecejointe(@PathParam("piecejointe_id") int piecejointe_id) {
        //
        List<Piecejointe> listeU = modelPlainte.getAllPiecejointe(piecejointe_id);
        //listeU.forEach((u)->{
        //  System.out.println("Element nom: "+u.nom);
        //});
        //
        //Todo todo = new Todo();
        //todo.setSummary("Application JSON Todo Summary");
        //todo.setDescription("Application JSON Todo Description");
        //
        //Todo todo2 = new Todo();
        //todo2.setSummary("Application JSON ");
        //todo2.setDescription("Application JSON ");

        return listeU;//Arrays.asList(todo,todo2);
    }

    //@Path("")
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savetPlainte(Piecejointe piecejointe) {
        int t = modelPlainte.savePiecejointe(piecejointe);
        /*
        System.out.println("votre element: "+
        Plainte.getDate()+":\n__:"+
            Plainte.getDate()+":\n__:"+
            Plainte.getEmail()+":\n__:"+
            Plainte.getStatut()+":\n__:"
        );
        */
        //

        ObjectNode json = mapper.createObjectNode();
        //
        //json.put("status", "ok");
        json.put("save", t);
        
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

}
