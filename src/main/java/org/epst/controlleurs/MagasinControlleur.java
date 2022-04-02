package org.epst.controlleurs;

import java.util.LinkedList;
import java.util.List;

import org.epst.beans.Magasin;
import org.epst.models.ModelMagasin;
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

@Path("/magasin")
public class MagasinControlleur {

    private static final ObjectMapper mapper = new ObjectMapper();
    ModelMagasin modelMagasin = new ModelMagasin();
    
    @Path("/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Magasin getMagasint(@PathParam("id") Long id) {
        System.out.println("le id: "+id);
        Magasin u = modelMagasin.getMagasin(id);
        //Todo todo = new Todo();
        //todo.setSummary(id);
        //todo.setDescription("Application JSON Todo Description");
        return u;
    }

    @Path("/all/{type}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Magasin> getAllMagasints(@PathParam("type") String type) {
        System.out.println("Element type: "+type);
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
    //Content-Type: text/plain;charset=UTF-8
    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response savetMagasint(Magasin Magasin) {
        String t = modelMagasin.saveMagasin(Magasin);
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
    public Response updateMagasint(Magasin Magasin) {

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
    public Response deleteMagasint(@PathParam("id") Long id) {
        int t = modelMagasin.supprimerMagasin(id);
        ObjectNode json = mapper.createObjectNode();
        //
        //json.put("status", "ok");
        json.put("supprimer", t);
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    @Path("recherche/{mot}")
    @GET()
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Magasin> rechercheMag(@PathParam("type")String mot){

        List<Magasin> liste = new LinkedList<Magasin>();
        
        return liste;

    }

}

