package com.epst.controlleurs;

import java.util.List;
import com.epst.beans.Utilisateur;
import com.epst.models.ModelUtilisateur;
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

@Path("/agent")
public class AgentControlleur {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    ModelUtilisateur modelUtilisateur = new ModelUtilisateur();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        // create a JSON string
        ObjectNode json = mapper.createObjectNode();
        json.put("EPST APP", "Server");
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Path("/{id}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public Utilisateur getAgent(@PathParam("id") int id) {
        Utilisateur u = modelUtilisateur.getUtilisateur(id);
        //Todo todo = new Todo();
        //todo.setSummary(id);
        //todo.setDescription("Application JSON Todo Description");
        return u;
    }

    @Path("/all")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Utilisateur> getAllAgents() {
        //
        List<Utilisateur> listeU = modelUtilisateur.getAllUtilisateur();
        listeU.forEach((u)->{
            System.out.println("Element nom: "+u.nom);
        });
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
    public Response savetAgent(Utilisateur utilisateur) {
        String t = modelUtilisateur.saveUtilisateur(utilisateur);
        System.out.println("votre element: "+
        utilisateur.getAdresse()+":\n__:"+
            utilisateur.getDate_de_naissance()+":\n__:"+
            utilisateur.getEmail()+":\n__:"+
            utilisateur.getNom()+":\n__:"+
            utilisateur.getNumero()+":\n__:"+
            utilisateur.getPostnom()+":\n__:"+
            utilisateur.getPrenom()+":\n__:"+
            utilisateur.getRole()+":\n__:"+
            utilisateur.getMatricule()+":\n__:"+
            utilisateur.getId_statut()
        );
        //

        ObjectNode json = mapper.createObjectNode();
        //
        json.put("status", "votre element: "+
        utilisateur.getAdresse()+":\n__:"+
            utilisateur.getDate_de_naissance()+":\n__:"+
            utilisateur.getEmail()+":\n__:"+
            utilisateur.getNom()+":\n__:"+
            utilisateur.getNumero()+":\n__:"+
            utilisateur.getPostnom()+":\n__:"+
            utilisateur.getPrenom()+":\n__:"+
            utilisateur.getRole()+":\n__:"+
            utilisateur.getMatricule()+":\n__:"+
            utilisateur.getId_statut());
        json.put("save", t);
        
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    @PUT()
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAgent(Utilisateur utilisateur) {

        int t = modelUtilisateur.miseaJourUtilisateur(utilisateur);
        System.out.println(utilisateur.adresse);
        
        ObjectNode json = mapper.createObjectNode();
        //
        json.put("mettre à jour", t);
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    @Path("/{id}")
    @DELETE()
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAgent(@PathParam("id") int id) {
        int t = modelUtilisateur.supprimerUtilisateur(id);
        ObjectNode json = mapper.createObjectNode();
        //
        //json.put("status", "ok");
        json.put("supprimer", t);
        return Response.status(Response.Status.CREATED).entity(json).build();
    }

    
}
