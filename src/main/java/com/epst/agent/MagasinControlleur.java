package com.epst.agent;

import java.util.List;

import com.epst.beans.Magasin;
import com.epst.models.ModelMagasin;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/magasin")
public class MagasinControlleur {

    private static final ObjectMapper mapper = new ObjectMapper();
    ModelMagasin modelMagasin = new ModelMagasin();
    
    @Path("/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Magasin getAgent(@PathParam("id") int id) {
        Magasin u = modelMagasin.getMagasin(id);
        //Todo todo = new Todo();
        //todo.setSummary(id);
        //todo.setDescription("Application JSON Todo Description");
        return u;
    }

    @Path("/all/{type}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Magasin> getAllAgents(@PathParam("type") int type) {
        //
        List<Magasin> listeU = modelMagasin.getAllMagasin(type);
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
    public Response savetAgent(Magasin Magasin) {
        int t = modelMagasin.saveMagasin(Magasin);
        System.out.println("votre element: "+
        Magasin.getDate()+":\n__:"+
            Magasin.getDescription()+":\n__:"+
            Magasin.getId()+":\n__:"+
            Magasin.getLibelle()+":\n__:"
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
    public Response updateAgent(Magasin Magasin) {

        int t = modelMagasin.miseaJourMagasin(Magasin);
        //System.out.println(Magasin.adresse);
        
        ObjectNode json = mapper.createObjectNode();
        //
        json.put("mettre à jour", t);
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    @Path("/{id}")
    @DELETE()
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAgent(@PathParam("id") int id) {
        int t = modelMagasin.supprimerMagasin(id);
        ObjectNode json = mapper.createObjectNode();
        //
        //json.put("status", "ok");
        json.put("supprimer", t);
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    
}
