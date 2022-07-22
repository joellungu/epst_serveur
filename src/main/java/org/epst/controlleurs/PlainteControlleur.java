package org.epst.controlleurs;


import java.util.HashMap;
import java.util.List;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.errors.MailjetSocketTimeoutException;
import com.mailjet.client.resource.Emailv31;
import org.epst.beans.NoteTraitementBean;
import org.epst.beans.Plainte;
import org.epst.models.ModelNoteTraitement;
import org.epst.models.ModelPlainte;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
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
    public Plainte getPlainte(@PathParam("id") Long id) {
        Plainte u = modelPlainte.getPlainte(id);
        //Todo todo = new Todo();
        //todo.setSummary(id);
        //todo.setDescription("Application JSON Todo Description");
        return u;
    }

    @Path("/all/{statut}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plainte> getAllPlaintes(@PathParam("statut") int statut) {
        //
        System.out.println("Le statut: "+statut);
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

    @Path("/reference/{reference}")
    @GET()
    @Produces(MediaType.APPLICATION_JSON)
    public List<Plainte> getAllPlaintesR(@PathParam("reference") String reference) {
        //
        List<Plainte> listeU = modelPlainte.getAllPlainteR(reference);
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
    public Response savetPlainte(Plainte plainte) {
        Long t = modelPlainte.savePlainte(plainte);
        if(plainte.getId_tiquet().equals("1")){
            System.out.println("Message: "+plainte.getMessage());
            Thread th = new Thread() {
                public void run() {
                    //
                    try {
                        send("",plainte.getMessage());
                        System.out.println("Email envoyé");
                    } catch (MailjetException e) {
                        System.out.println("Email non envoyé");
                        throw new RuntimeException(e);
                    } catch (MailjetSocketTimeoutException e) {
                        System.out.println("Email non envoyé");
                        throw new RuntimeException(e);
                    }
                    //

                }
            };
            th.start();
        }else{
            System.out.println("Autre ethiquette: "+plainte.getId_tiquet());
            System.out.println("Autre ethiquette: "+plainte.getMessage());
        }
        System.out.println("votre element: "+
                plainte.getTelephone()+":\n__:"+
                plainte.getDate()+":\n__:"+
                plainte.getEmail()+":\n__:"+
                plainte.getEnvoyeur()+":\n__:"
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

    @Path("/noter")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response noterMa(HashMap<String, String> map){//
        NoteTraitementBean noteTraitementBean = new NoteTraitementBean();
        noteTraitementBean.setNote(map.get("note"));
        noteTraitementBean.setReference(map.get("reference"));
        noteTraitementBean.setNomIdmin(map.get("nomIdmin"));
        noteTraitementBean.setId(1L);

        System.out.println("Le note: "+map.get("note"));
        System.out.println("Le nom: "+map.get("nomIdmin"));
        System.out.println("Le ref: "+map.get("reference"));
        /*
        System.out.println("Le note: "+noteTraitementBean.getNote());
        */
        ModelNoteTraitement modelNoteTraitement = new ModelNoteTraitement();
        Long v = modelNoteTraitement.saveNote(noteTraitementBean);
        ObjectNode json = mapper.createObjectNode();
        json.put("status", "v");
        return Response.status(Response.Status.CREATED).entity("ok").build();
    }

    public void send(String from, String message) throws MailjetException, MailjetSocketTimeoutException {
        MailjetClient client;
        MailjetRequest request;
        MailjetResponse response;
        //
        //StringBuilder message = new StringBuilder();
        //
        client = new MailjetClient("6f319c7eabca73a75926580bf1291102",
                "7f4ef3362f04f20e9fcbbdaf5fea596e", new ClientOptions("v3.1"));
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "mmuseghe@gmail.com")
                                        .put("Name", "Pierre Museghe"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "lungujoel138@gmail.com")
                                                .put("Name", "Joel Lungu")))
                                .put(Emailv31.Message.SUBJECT, "Violence basé sur le genre")
                                .put(Emailv31.Message.TEXTPART, "Contenu:\n"+message)
                                .put(Emailv31.Message.HTMLPART, "<h3>Voici le lien du fichier<br><br><a href=\"https://www.mailjet.com/\">Mailjet</a>!</h3><h4>"+message+"</h4>")));
        response = client.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
    }
}
