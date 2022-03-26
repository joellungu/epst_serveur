package org.epst.controlleurs;


import java.util.List;

import org.epst.beans.Plainte;
import org.epst.models.ModelPlainte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/plainte")
public class PlainteControlleur {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    ModelPlainte modelPlainte = new ModelPlainte();
    
    @Path("/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Plainte getPlainte(@PathParam("id") int id) {
        Plainte u = modelPlainte.getPlainte(id);
        //Todo todo = new Todo();
        //todo.setSummary(id);
        //todo.setDescription("Application JSON Todo Description");
        return u;
    }

    @Path("/all/{statut}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plainte> getAllPlaintes(@PathParam("statut") String statut) {
        //
        List<Plainte> listeU = modelPlainte.getAllPlainte(statut);
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
    public Response savetPlainte(Plainte Plainte) {
        Long t = modelPlainte.savePlainte(Plainte);
        System.out.println("votre element: "+
        Plainte.getTelephone()+":\n__:"+
            Plainte.getDate()+":\n__:"+
            Plainte.getEmail()+":\n__:"+
            Plainte.getEnvoyeur()+":\n__:"
        );
        //

        ObjectNode json = mapper.createObjectNode();
        //
        //json.put("status", "ok");
        json.put("save", t);
        
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    @PUT()
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePlainte(Plainte Plainte) {

        int t = modelPlainte.miseaJourPlainte(Plainte);
        //System.out.println(Plainte.adresse);
        
        ObjectNode json = mapper.createObjectNode();
        //
        json.put("mettre à jour", t);
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

}
