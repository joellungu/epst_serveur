package org.epst.controlleurs;

import java.util.List;

import org.epst.beans.Piecejointe;
import org.epst.beans.Plainte;
import org.epst.models.ModelPlainte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    @Path("/{piecejointe_id}/{type}")
    @POST()
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response savetPlainte(@PathParam("piecejointe_id") Long piecejointe_id, @PathParam("type") String type, byte[] piecejointe) {
        int t = modelPlainte.savePiecejointe(piecejointe_id, type, piecejointe);
        
        System.out.println("piecejointe_id: "+piecejointe_id+"// type "+type+"");
        System.out.println("votre element piece jointe_______: "+
        piecejointe_id+":\n__:"+
        type+":\n__:"
        );
        
        //

        ObjectNode json = mapper.createObjectNode();
        //
        //json.put("status", "ok");
        json.put("save", t);
        
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

}
